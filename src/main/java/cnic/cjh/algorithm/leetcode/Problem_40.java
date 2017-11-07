package cnic.cjh.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/combination-sum-ii/description/
 */
public class Problem_40 {
    public static void main(String[] args)
    {
        int[] candidates = {10,1,2,7,6,1,5};
        new Problem_40().combinationSum2(candidates,8);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length ==0) return new ArrayList<List<Integer>>();

        quickSort(candidates,0,candidates.length-1);


        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Map<Integer,List<List<Integer>>> map = new HashMap<Integer,List<List<Integer>>>();
        List<Integer> temp = null;
        List<List<Integer>> all = null;
        if(candidates[0] == target)
        {
            temp = new ArrayList<Integer>();
            temp.add(candidates[0]);
            result.add(temp);
            return result;
        }
        else if(candidates[0] + candidates[0] > target)
        {
            return new ArrayList<List<Integer>>();
        }
        else
        {
            temp = new ArrayList<Integer>();
            temp.add(candidates[0]);
            all = new ArrayList<List<Integer>>();
            all.add(temp);
            map.put(candidates[0],all);
        }
        for(int i=1; i<candidates.length; i++)
        {
            if(candidates[i] == target)
            {
                temp = new ArrayList<Integer>();
                temp.add(candidates[i]);
                result.add(temp);
                return result;
            }
            else if(candidates[i] > target)
            {
                return result;
            }

            //遍历map所有的元素
            Set<Integer> sumSet = new TreeSet<Integer>(map.keySet());
            for(int sum : sumSet)
            {
                //找到目标
                if(sum + candidates[i] == target)
                {
                    all = map.get(sum);
                    for(List<Integer> list : all)
                    {
                        list.add(candidates[i]);
                        result.add(list);
                    }
                    //移出
                    map.remove(sum);
                }
                //和超出阈值
                else if(sum + candidates[i] > target)
                {
                    map.remove(sum);
                }
                //sum + candidates[i] < target
                else
                {
                    //加入
                    all = map.get(sum);
                    for(List<Integer> list : all)
                    {
                        List<Integer> newList = new ArrayList<Integer>(list);
                        newList.add(candidates[i]);
                        List<List<Integer>> newAll = map.get(sum + candidates[i]);
                        if(newAll == null)
                        {
                            newAll = new ArrayList<List<Integer>>();
                        }
                        //判断不重复
                        boolean repeat = false;
                        for(List<Integer> l:newAll)
                        {
                            if(l.size() != newList.size())
                            {
                                continue;
                            }
                            boolean same = true;
                            for(int j=0; j<l.size(); j++)
                            {
                                if(newList.get(j) != l.get(j))
                                {
                                    same = false;
                                    break;
                                }
                            }
                            //有重复
                            if(same)
                            {
                                repeat = true;
                                break;
                            }
                        }
                        //无重复，加入
                        if(repeat == false)
                        {
                            newAll.add(newList);
                        }
                        map.put(sum + candidates[i],newAll);
                    }
                }
            }

            if(candidates[i] + candidates[i] <= target)
            {
                //去除重复
                if(candidates[i] != candidates[i-1])
                {
                    //加上元素自身
                    all = map.get(candidates[i]);
                    if(all == null)
                    {
                        all = new ArrayList<List<Integer>>();
                    }
                    temp = new ArrayList<Integer>();
                    temp.add(candidates[i]);
                    all.add(temp);
                    map.put(candidates[i],all);
                }
            }
        }
        return result;
    }

    //快速排序
    public void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int base = numbers[start]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值
            int i = start, j = end;
            do {
                while ((numbers[i] < base) && (i < end))
                    i++;
                while ((numbers[j] > base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                quickSort(numbers, start, j);
            if (end > i)
                quickSort(numbers, i, end);
        }
    }
}
