package com.cmm.zjwz.utils;

import java.util.List;

/**
 * Created by cmm on 2017/6/7.
 * 排序工具类
 */

public class SortAlgorithmUtil {

    /**
     * 交换排序法/冒泡排序法 ：对相邻两元素进行比较、进行交换，嵌套for每次循环依据条件数组将最大元素往后移动，下一次循环将剩余元素进行类似操作。
     * 适用：初始状态基本有序，不需要进行反复元素交换
     *
     * @param <E>
     *            list中的泛型实体对象              long型
     *
     * @param rawDatas
     *            进行排序的数据集合
     * @param condition
     *            要排序的条件，来自数据集合的每一个对象代表
     * @param sortType
     *            排序类型 正序/倒序
     */
    public static <E> void exchangeSort(List<E> rawDatas, long[] condition, String sortType) {
        if (sortType.equals("asc")) { // 正序
            for (int i = 1; i < condition.length; i++) {
                // 比较条件数组中相邻的两个元素，较大的数往后移动
                for (int j = 0; j < condition.length - i; j++) {
                    if (condition[j] > condition[j + 1]) {
                        // 交换相邻两个元素的位置
                        swapElement(rawDatas, condition, j, j + 1);
                    }
                }
            }
        } else if (sortType.equals("desc")) { // 倒序
            for (int i = 1; i < condition.length; i++) {
                // 比较条件数组中相邻的两个元素，较小的数往后移动
                for (int j = 0; j < condition.length - i; j++) {
                    if (condition[j] < condition[j + 1]) {
                        // 交换相邻两个元素的位置
                        swapElement(rawDatas, condition, j, j + 1);
                    }
                }
            }
        }

    }

    /**
     * 选择排序法/直接排序法 ：嵌套for每次循环依据排序条件从待排序的数组元素中选出最小或最大的一个元素，并依次排列组合。
     * 特点：交换次数比冒泡排序少，速度较冒泡排序高
     *
     * @param <E>
     *            list中的泛型实体对象            long型
     *
     * @param rawDatas
     *            进行排序的数据集合
     * @param condition
     *            要排序的条件，来自数据集合的每一个对象代表
     * @param sortType
     *            排序类型 正序/倒序
     */
    public static <E> void selectSort(List<E> rawDatas, long[] condition, String sortType) {
        if (sortType.equals("asc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] > condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement(rawDatas, condition, condition.length - i, record);
            }
        } else if (sortType.equals("desc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] < condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement(rawDatas, condition, condition.length - i, record);
            }
        }

    }

    /**
     * 插入排序法 ：嵌套for每次循环将新增的一项元素与已处理的元素进行依次比较并排在适当位置，每次将生成一个新的有序数组
     * 特点：性能较好。
     * 适用：当记录规模较小时
     *
     * @param <E>
     *            list中的泛型实体对象            long型
     *
     * @param rawDatas
     *            进行排序的数据集合
     * @param condition
     *            要排序的条件，来自数据集合的每一个对象代表
     * @param sortType
     *            排序类型 正序/倒序
     */
    public static <E> void insertSort(List<E> rawDatas, long[] condition, String sortType) {
        if (sortType.equals("asc")) {
            for (int i = 1; i < condition.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (condition[j] > condition[i]) {
                        // 交换x和y两个元素
                        swapElement(rawDatas, condition, i, j);
                    }
                }
            }
        } else if (sortType.equals("desc")) {
            for (int i = 1; i < condition.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (condition[j] < condition[i]) {
                        // 交换x和y两个元素
                        swapElement(rawDatas, condition, i, j);
                    }
                }
            }
        }

    }

    /**
     * 交换指定元素的位置
     *
     * @param <E>
     * @param rawDatas
     * @param condition
     * @param x
     * @param y
     */
    private static <E> void swapElement(List<E> rawDatas, long[] condition, int x, int y) {

        long tempC = condition[x];
        condition[x] = condition[y];
        condition[y] = tempC;
        if (rawDatas != null) {
            E temp = rawDatas.get(x);
            rawDatas.set(x, rawDatas.get(y));
            rawDatas.set(y, temp);
        }
    }

    /**
     * 交换指定元素的位置
     * int
     */
    private static <E> void swapElement(List<E> rawDatas, int[] condition, int x, int y) {

        int tempC = condition[x];
        condition[x] = condition[y];
        condition[y] = tempC;
        if (rawDatas != null) {
            E temp = rawDatas.get(x);
            rawDatas.set(x, rawDatas.get(y));
            rawDatas.set(y, temp);
        }
    }

    /**
     * 选择排序法/直接排序法 ：
     *
     * 从大到小
     * double型
     */
    public static <E> void maxTOSmall(List<E> rawDatas, double[] condition, String sortType) {
        if (sortType.equals("asc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] < condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement2(rawDatas, condition, condition.length - i, record);
            }
        } else if (sortType.equals("desc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] > condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement2(rawDatas, condition, condition.length - i, record);
            }
        }
    }

    /**
     *从小到大
     * double型
     */
    public static <E> void selectSort(List<E> rawDatas, double[] condition, String sortType) {
        if (sortType.equals("asc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] > condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement2(rawDatas, condition, condition.length - i, record);
            }
        } else if (sortType.equals("desc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] < condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement2(rawDatas, condition, condition.length - i, record);
            }
        }
    }


    /**
     *从小到大
     * int型
     */
    public static <E> void selectSort(List<E> rawDatas, int[] condition, String sortType) {
        if (sortType.equals("asc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] > condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement3(rawDatas, condition, condition.length - i, record);
            }
        } else if (sortType.equals("desc")) {
            int record;
            for (int i = 1; i < condition.length; i++) {
                record = 0;
                for (int j = 1; j <= condition.length - i; j++) {
                    if (condition[j] < condition[record]) {
                        record = j;
                    }
                }
                // 交换condition.length - i和当前最小元素
                swapElement3(rawDatas, condition, condition.length - i, record);
            }
        }

    }


    private static <E> void swapElement2(List<E> rawDatas, double[] condition, int x, int y) {

        double tempC = condition[x];
        condition[x] = condition[y];
        condition[y] = tempC;
        if (rawDatas != null) {
            E temp = rawDatas.get(x);
            rawDatas.set(x, rawDatas.get(y));
            rawDatas.set(y, temp);
        }
    }

    private static <E> void swapElement3(List<E> rawDatas, int[] condition, int x, int y) {

        int tempC = condition[x];
        condition[x] = condition[y];
        condition[y] = tempC;
        if (rawDatas != null) {
            E temp = rawDatas.get(x);
            rawDatas.set(x, rawDatas.get(y));
            rawDatas.set(y, temp);
        }
    }

}
