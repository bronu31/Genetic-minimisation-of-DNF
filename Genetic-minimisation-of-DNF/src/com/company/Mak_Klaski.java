package com.company;

import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Mak_Klaski {

    public ArrayList<String> Start(String ones){
        byte leng= (byte) (Math.log(ones.length())/Math.log(2));
        char[] converted = ones.toCharArray();
        ArrayList<String> Ones=new ArrayList();
        for(int i=0;i!=converted.length;i++){
            if (converted[i]=='1')
                Ones.add(String.format("%"+leng+"s",Integer.toBinaryString(i)).replace(" ", "0"));
        }
        Ones.trimToSize();
        return Ones;
    }
    public Map<Integer, Array_of_jumps> Sorting(ArrayList<String> ones){
        Map<Integer,Array_of_jumps> combinations=new HashMap<Integer,Array_of_jumps>();
        for(int i=0;i!=ones.size();i++){
            if (combinations.containsKey(StringUtils.countMatches(ones.get(i),"1"))){
                Array_of_jumps jumps = combinations.get((StringUtils.countMatches(ones.get(i),"1")));
                jumps.addJump(ones.get(i));

            }
            else {
                combinations.put(StringUtils.countMatches(ones.get(i),"1"),new Array_of_jumps(ones.get(i)));
            }
        }
        return combinations;
    }
    public ArrayList<String> GlueJumps(Map<Integer, Array_of_jumps> glue){
        char[] FirstPart;
        char[] SecondPart;
        byte position=0;
        byte More=0;
        ArrayList<Removers> list= new ArrayList<>();
        ArrayList<String> strings=new ArrayList<>();
        ArrayList<String> EndArray=new ArrayList<>();
        System.out.println(glue);
        Integer[] sir = new Integer[glue.keySet().size()];
        glue.keySet().toArray(sir);

        System.out.println(sir[1]+" daaaa");
        for (int i=0;i!=sir.length;i++){
            strings.addAll(glue.get(sir[i]).list);
            for (byte j=0;j!=strings.size();j++){
                list.add(new Removers(strings.get(j)));
            }
            strings.clear();
        }
        for (int i=0;i!=list.size();i++){
            FirstPart=list.get(i).part.toCharArray();
            for (int j=i+1;j!=list.size();j++){
                SecondPart=list.get(j).part.toCharArray();
                for(byte z=0;z!=FirstPart.length;z++){
                    if (FirstPart[z]!=SecondPart[z]){
                        position=z;
                        More++;
                    }
                }
                if (More==1){
                    list.get(i).set_True();
                    list.get(j).set_True();
                    SecondPart[position]='-';
                    EndArray.add(String.valueOf(SecondPart));
                }
                More=0;
            }
        }
        for (int i=0;i!=list.size();i++){
            if(!list.get(i).is_true()){
                EndArray.add(list.get(i).part);
            }
        }
        Set<String> set = new HashSet<>(EndArray);
        EndArray.clear();
        EndArray.addAll(set);
        return EndArray;
    }
    public Map<Integer, ArrayList<String>> Map_Of_Covers_by_Jumps(ArrayList<String> what_to_Cover, ArrayList<String> phase_One){
        Map<Integer, ArrayList<String>> Phase_Two= new HashMap<>();
        char[] cover;
        char[] one;
        ArrayList<String> carier;
        ArrayList<String> adder;

        for(int j=0;j!=what_to_Cover.size();j++){
            cover=what_to_Cover.get(j).toCharArray();
            for (int i=0;i!=phase_One.size();i++){
                one=phase_One.get(i).toCharArray();
                for(byte z=0;z!=one.length;z++){
                    if(one[z]=='-')
                        one[z]=cover[z];
                }
                if (String.valueOf(cover).equals(String.valueOf(one))){
                    if(Phase_Two.containsKey(i)){
                        adder=Phase_Two.get(i);
                        adder.add(String.valueOf(one));
                    }
                    else {
                        carier=new ArrayList<>();
                        carier.add(String.valueOf(one));
                        Phase_Two.put(i,carier);
                    }
                }
                }
            }

        return Phase_Two;
    }

    public Map<Integer, ArrayList<Integer>> Map_Of_Covers_by_Ones(ArrayList<String> what_to_Cover, Map<Integer, ArrayList<String>> jumps_by_Number){
        Map<Integer, ArrayList<Integer>> Jumps_by_Ones= new HashMap<>();
        System.out.println("da  "+jumps_by_Number);
        ArrayList<Integer> carier;
        ArrayList<Integer> adder;
        ArrayList<String> link;
        for (int j=0;j!=jumps_by_Number.size();j++){
            link=jumps_by_Number.get(j);
            for (int z=0;z!=link.size();z++){
                for (int i=0;i!=what_to_Cover.size();i++){
                    if (link.get(z).equals(what_to_Cover.get(i))){
                        if(Jumps_by_Ones.containsKey(i)){
                            adder=Jumps_by_Ones.get(i);
                            adder.add(j);
                        }
                        else {
                            carier=new ArrayList<>();
                            carier.add(j);
                            Jumps_by_Ones.put(i,carier);
                        }
                    }
                }
            }
        }
        System.out.println(Jumps_by_Ones);
        return Jumps_by_Ones;
    }


}
class Array_of_jumps{
    ArrayList<String> list;

    public Array_of_jumps(String jump) {
        this.list = new ArrayList<String>();
        this.list.add(jump);
    }

    public void addJump(String jump){
        if (!this.list.contains(jump)){
            this.list.add(jump);
        }
    }

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return " " + list ;
    }
}
