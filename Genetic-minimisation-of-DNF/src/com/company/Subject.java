package com.company;

import java.util.*;

public class Subject {
    int weight;

    public void setDNA(ArrayList<Integer> DNA) {
        this.DNA = DNA;
    }

    public ArrayList<Integer> getDNA() {
        return DNA;
    }

    public static ArrayList<Subject> Mutation(ArrayList<Subject> population, Map<Integer, ArrayList<Integer>> jumps_by_ones, float mutation) {
        Random random=new Random();
        int rand;
        ArrayList<Integer> copy = null;
        for (int i=0;i!=population.size();i++){
            if(Math.random()<mutation){
                copy=population.get(i).getDNA();
                rand=random.nextInt(copy.size());
                if (jumps_by_ones.get(rand).size()==1)
                    continue;
                copy.set(rand,jumps_by_ones.get(rand).get(random.nextInt(jumps_by_ones.get(rand).size())));
                population.get(i).setDNA(copy);

           }

        }
        return population;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    ArrayList<Integer> DNA;

    public Subject(ArrayList<Integer> DNA) {
        this.weight = 0;
        this.DNA = DNA;
    }

    @Override
    public String toString() {
        return "Subject{ " +
                "weight= " + weight +
                ", DNA= '" + DNA + '\'' +
                " }"+ "\n";
    }
    public static ArrayList<Subject> Number_of_Difference(ArrayList<Subject> population){
        for(int i=0;i!=population.size();i++)
            population.get(i).setWeight(new HashSet<>(population.get(i).DNA).size());
        return population;
    }

    public static ArrayList<Subject> Selection(ArrayList<Subject> Population,int children){ //perhaps add more then 2 parents
        Random random=new Random();
        ArrayList<Subject> pop=new ArrayList<>();
        ArrayList<Integer> Parent1= Population.get(0).DNA;
        ArrayList<Integer> Parent2= Population.get(1).DNA;
        ArrayList<Integer> First_Part;
        ArrayList<Integer> Second_Part;
        int border;
        for(int i=0;i!=children;i++){ border=random.nextInt(Parent1.size());
        First_Part=new ArrayList<>(Parent1.subList(0,border));
        Second_Part=new ArrayList<>(Parent1.subList(border,Parent2.size()));
        First_Part.addAll(Second_Part);
        pop.add(new Subject((ArrayList<Integer>) First_Part.clone()));
        First_Part.clear();
        Second_Part.clear();
        }

        border=2;
        while(pop.size()!=Population.size()){
            pop.add(new Subject(pop.get(border).DNA));
        }
        return pop;
    }

}
class SubjectSorter implements Comparator <Subject>{

    @Override
    public int compare(Subject o1, Subject o2) {
        return Integer.compare(o1.weight, o2.weight);
    }
}



