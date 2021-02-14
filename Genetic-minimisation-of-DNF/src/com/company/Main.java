package com.company;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

import static com.company.Subject.Number_of_Difference;

public class Main {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int pop=in.nextInt();
        int children=in.nextInt();
        String obj="";
        float mutation=in.nextFloat();



        Mak_Klaski method = new Mak_Klaski();
        ArrayList<String> What_to_Cover = method.Start("00110101010110010111010010001001"); // Finding what elements we need to minimize
        System.out.println(What_to_Cover);
        Map<Integer, Array_of_jumps> Glue= new HashMap<>();
        ArrayList<String> Phase_One = null;
        Glue = method.Sorting(What_to_Cover); //Sorting by the number of ones
        Phase_One= method.GlueJumps(Glue); // Glue what pairs of string we can and purge duplicates
        for (byte i=0;i!=65;i++){
            Glue = method.Sorting(Phase_One);
            Phase_One=method.GlueJumps(Glue);
        }
        System.out.println("DA "+Phase_One);
        Map<Integer, ArrayList<String>> Jumps_by_Number = method.Map_Of_Covers_by_Jumps(What_to_Cover, Phase_One);
        Map<Integer, ArrayList<Integer>> Jumps_by_Ones = method.Map_Of_Covers_by_Ones(What_to_Cover, Jumps_by_Number);
        ArrayList<Subject> Population= new ArrayList<>();
        ArrayList<Integer> Genome;
        Random random=new Random();
        System.out.println(Jumps_by_Ones);
        for (int j=0;j!=pop;j++){
            Genome=new ArrayList<>();
            for (int siz=0;siz!=What_to_Cover.size();siz++){
                Genome.add(Jumps_by_Ones.get(siz).get(random.nextInt(Jumps_by_Ones.get(siz).size())));
            }
            Population.add(new Subject(Genome));
        }
        for(int i=0;i!=5000;i++) {
            Population = Number_of_Difference(Population);
            Collections.sort(Population, new SubjectSorter());
            Population = Subject.Selection(Population, children);
            Population=Subject.Mutation(Population,Jumps_by_Ones,mutation);
        }
        Population = Number_of_Difference(Population);
        Collections.sort(Population, new SubjectSorter());
        System.out.println(Jumps_by_Ones);
        System.out.println(Population);




    }
}
