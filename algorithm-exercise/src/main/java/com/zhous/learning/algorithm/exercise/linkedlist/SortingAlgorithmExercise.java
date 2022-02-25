package com.zhous.learning.algorithm.exercise.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序算法练习
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/14
 */
public class SortingAlgorithmExercise {

    /**
     * 冒泡排序:
     * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列;
     * 一次比较两个元素,如果它们的顺序错误就把它们交换过来;
     * 走访数列的工作是重复的进行,直到没有再需要交换的元素，也就是说该数列已经排序完成。
     *
     * @param sortArray
     * @return
     */
    public static int[] bubbleSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        //每一趟排序从头开始两两比较进行交换，将最大或者最小的元素放到数组末尾已经有序的序列中，需要排序的总趟数等于数组的长度
        for (int i = 0; i < sortArray.length; i++) {
            //标志本趟排序中是否发生了交换，若没有发生交换则说明序列已经有序，可以直接结束排序
            boolean isSwap = false;
            for (int j = 0; j < sortArray.length - 1 - i; j++) {
                //每次从头开始进行两两比较，第i趟排序则表示数组后面的i个元素已经有序，所以 j<sortArray.length-1-i
                if (sortArray[j] > sortArray[j + 1]) {
                    //每趟排序将最大的元素放到数组末尾，也就是升序排序
                    int temp = sortArray[j];
                    sortArray[j] = sortArray[j + 1];
                    sortArray[j + 1] = temp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                //本趟排序没有发生元素交换则整个序列已经有序，直接结束
                break;
            }
        }
        return sortArray;
    }

    /**
     * 选择排序:
     * 选择排序是一种简单直观的排序算法。
     * 首先在未排序序列中找到最小（大）元素，存放到已排序序列的起始位置;
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     *
     * @param sortArray
     * @return
     */
    public static int[] selectionSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        //每一趟排序寻找一个最小的元素放到数组开头已经有序的序列中，排序的总趟数为数组的长度
        for (int i = 0; i < sortArray.length; i++) {
            //本趟排序寻找的的最小元素的索引，默认为i，i表示本趟排序最终寻找的最小元素应该插入的位置
            int minIndex = i;
            for (int j = i + 1; j < sortArray.length; j++) {
                //第i趟排序说明数组开头的i个元素已经有序，所以寻找最小的元素时从第i+1个元素开始
                if (sortArray[j] < sortArray[minIndex]) {
                    //元素的值比当前最小元素的值小时，更新本趟排序寻找的最小元素索引
                    minIndex = j;
                }
            }
            //如果最小元素的索引不是i则交换索引为minIndex和i的两个元素
            if (minIndex != i) {
                int temp = sortArray[i];
                sortArray[i] = sortArray[minIndex];
                sortArray[minIndex] = temp;
            }
        }
        return sortArray;
    }

    /**
     * 插入排序:
     * 插入排序是一种简单直观的排序算法。
     * 它是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入,
     * 在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     *
     * @param sortArray
     * @return
     */
    public static int[] insertionSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        //假设数组第一个元素是已排序序列，其余元素是未排序序列,所以需要扫描（或者说排序）的总次数是未排序序列的元素个数,即：sortArray.length-1。因此i应该小于sortArray.length-1
        //i表示已排序序列的最后一个元素的索引，每次需要从i开始向前扫描
        for (int i = 0; i < sortArray.length - 1; i++) {
            //在已经排序的序列中扫描的索引,初始为已排序序列的最后一个元素的索引
            int index = i;
            //未排序序列的第一个元素索引，随着排序的进行，未排序序列中的元素将会逐渐插入到已排序序列中，所以未排序序列会越来越短.
            //即：未排序序列的第一个元素索引将会逐渐增大直到数组末尾
            int temp = sortArray[index + 1];
            //在已排序序列中从最后一个元素开始向前扫描，需要反复把已排序元素逐步向后挪位，直到找到最新元素应该插入的位置，然后结束
            while (index >= 0 && temp < sortArray[index]) {
                sortArray[index + 1] = sortArray[index];
                index--;
            }
            //程序执行到这里已经找到了最新元素的插入位置：index+1，将最新元素temp插入该位置
            sortArray[index + 1] = temp;
        }
        return sortArray;
    }

    /**
     * 希尔排序：
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
     * 希尔排序是把记录按一定增量分组（例如：增量为5，那么索引为0,5,10，15....的这些元素为一组），对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的元素越来越多，当增量减至1时，所有元素被分成一组，算法便终止。
     *
     * @param sortArray
     * @return
     */
    public static int[] heerSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        //增量
        int increment = sortArray.length / 2;
        while (increment > 0) {
            //这里和直接插入排序区别在于：每次向前扫描和向后挪位的间隔元素由1个变为增量increment个
            for (int i = increment; i < sortArray.length; i++) {
                int index = i - increment;
                int temp = sortArray[index + increment];
                while (index >= 0 && temp < sortArray[index]) {
                    sortArray[index + increment] = sortArray[index];
                    index -= increment;
                }
                sortArray[index + increment] = temp;
            }
            //增量每次缩小一半
            increment /= 2;
        }
        return sortArray;
    }

    /**
     * 归并排序:
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序;
     * 对于子序列递归采用归并排序，直到子序列元素个数为1
     *
     * @param sortArray
     * @return
     */
    public static int[] mergeSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        //将序列拆分为两个子序列
        int mid = sortArray.length / 2;
        int[] left = Arrays.copyOfRange(sortArray, 0, mid);
        int[] right = Arrays.copyOfRange(sortArray, mid, sortArray.length);
        //合并两个两个子序列，对于两个子序列递归使用归并排序
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 将两个归并排序拆分的两个子序列合并为一个序列
     *
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(int[] left, int[] right) {
        //新建一个数组，大小为两个待合并的数组的长度之和，存放合并结果
        int[] result = new int[left.length + right.length];
        //开始合并
        for (int index = 0, index_left = 0, index_right = 0; index < result.length; index++) {
            if (index_left >= left.length) {
                //左边的序列已经全部合并到了结果中，则直接将右边的序列合并到结果中
                result[index] = right[index_right++];
            } else if (index_right >= right.length) {
                //右边的序列已经全部合并到了结果中，则直接将左边的序列合并到结果中
                result[index] = left[index_left++];
            } else if (left[index_left] < right[index_right]) {
                //判断左边序列和右边序列当前要合并的元素的大小，保证合并的后的结果依然有序
                result[index] = left[index_left++];
            } else {
                result[index] = right[index_right++];
            }
        }
        return result;
    }

    /**
     * 快速排序：
     * 通过一趟排序将待排记录按照一个基准值分隔成独立的两部分，其中一部分记录的值均比另一部分的值小
     * 对这两部分记录继续进行快速排序，以达到整个序列有序。
     *
     * @param sortArray
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSort(int[] sortArray, int start, int end) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        int index = partition(sortArray, start, end);
        if (index > start) {
            quickSort(sortArray, start, index - 1);
        }
        if (index < end) {
            quickSort(sortArray, index + 1, end);
        }
        return sortArray;
    }

    /**
     * 对待排序数组的指定范围按照基准值分隔成两个部分，其中一部分的值均比另一部分的值小，并且返回分隔基准值的索引位置
     *
     * @param sortArray
     * @param start     开始范围
     * @param end       结束范围
     * @return
     */
    private static int partition(int[] sortArray, int start, int end) {
        //选取的基准值索引，随机选取一个
        int benchmark_index = start + (int) ((end - start + 1) * Math.random());
        //分隔成的两个部分中左边部分的索引
        int index = start;
        //将基准值放到最后一个
        swapArrayElement(sortArray, benchmark_index, end);
        //循环遍历进行分隔，只需要将所有比基准值小的元素放到基准值的左边部分，那么右边部分就是比基准值大的所有元素
        for (int i = start; i <= end; i++) {
            //sortArray[end]就是基准值
            if (sortArray[i] <= sortArray[end]) {
                //当前元素比基准值小，则需要将当前元素放到左边部分，
                //当前元素等于基准值则表示已经分隔完毕，此时index指向的位置应该是基准值所在的位置，此时需要将基准值放到此位置，通过下面的交换来完成
                if (i > index) {
                    //当前元素比基准值小并且当前元素的索引值比左边部分的索引大，则需要交换元素到左边部分，左边部分的范围应该是start——>index
                    //交换数组中两个索引对应的元素值
                    swapArrayElement(sortArray, index, i);
                }
                if (i < end) {
                    //增加左边部分的索引，若i=end则表示index当前是基准值所在的位置，不需要再自增
                    index++;
                }
            }
        }
        return index;
    }

    /**
     * 堆排序：
     * 利用堆这种数据结构所设计的一种排序算法。堆是一个近似完全二叉树的结构，并同时满足小（大）根堆的性质：即父结点的键值或索引总是小于（或者大于）等于它的子节点。
     * 将所有待排序的元素序列构造大根堆，然后将根节点（此时根节点最大）与序列最后一个元素交换位置，此时最后一个元素最大，然后将最后一个元素剔除，也就是放到有序序列中。
     * 针对剩下的元素不断重复上述步骤，直到剩下的元素为1
     *
     * @param sortArray
     * @return
     */
    public static int[] heapSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        for (int i = sortArray.length / 2 - 1; i >= 0; i--) {
            //从最后一个非叶子节点开始向上构造大根堆，最后一个非叶子节点的索引为 sortArray.length/2-1
            adjustHeap(sortArray, i, sortArray.length);
        }
        for (int j = sortArray.length - 1; j > 0; j--) {
            //经过前面的构造，此时根节点已经是最大的元素
            //将根节点和最后一个节点交换位置
            swapArrayElement(sortArray, 0, j);
            //对剩下的元素继续构造大根堆
            adjustHeap(sortArray, 0, j);
        }
        return sortArray;
    }

    /**
     * 调整该节点使之符合大根堆（该节点的值比左右孩子都大）
     *
     * @param array
     * @param adjustNodeIndex 待调整的节点索引
     * @param length          调整的范围
     */
    public static void adjustHeap(int[] array, int adjustNodeIndex, int length) {
        //最大元素的索引，默认为当前调整的节点索引
        int maxIndex = adjustNodeIndex;
        //当前节点的左孩子索引
        int leftIndex = 2 * adjustNodeIndex + 1;
        //当前节点的右孩子索引
        int rightIndex = 2 * adjustNodeIndex + 2;
        //若左孩子的值比最大元素大，则更新最大元素的索引
        if (leftIndex < length && array[leftIndex] > array[maxIndex]) {
            maxIndex = leftIndex;
        }
        //若右孩子的值比最大元素大，则更新最大元素的索引
        if (rightIndex < length && array[rightIndex] > array[maxIndex]) {
            maxIndex = rightIndex;
        }
        //如果父节点不是最大值，调整父节点为最大值并且调整与父节点交换的节点符合大根堆
        if (maxIndex != adjustNodeIndex) {
            swapArrayElement(array, maxIndex, adjustNodeIndex);
            adjustHeap(array, maxIndex, length);
        }
    }

    /**
     * 计数排序：
     * 计数排序使用一个额外的数组C对应存放待排序数组中每个元素的频数（出现的次数）。
     * 然后根据数组C来将待排序数组的元素排到正确的位置，它只能对整数进行排序。
     *
     * @param sortArray
     * @return
     */
    public static int[] countingSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        int min = sortArray[0];//待排数组最小值
        int max = sortArray[0];//待排数组最大值
        for (int i = 0; i < sortArray.length; i++) {//查找最小值和最大值
            if (sortArray[i] < min) {
                min = sortArray[i];
            }
            if (sortArray[i] > max) {
                max = sortArray[i];
            }
        }
        int[] rateArray = new int[max - min + 1];//创建频数数组,大小为待排数组中数据出现的所有可能情况，也即是：max-min+1
        Arrays.fill(rateArray, 0);//全部填充为0
        for (int i = 0; i < sortArray.length; i++) {//遍历待排数组，统计每个数字出现的次数,每个元素在频数数组中的相对位置为：元素值-最小值
            rateArray[sortArray[i] - min]++;
        }
        int index = 0;
        int index_rate = 0;
        while (index_rate < rateArray.length) {//依次取出所有已经排序的元素
            if (rateArray[index_rate] != 0) {
                sortArray[index++] = min + index_rate;//通过相对位置恢复元素的本来值
                rateArray[index_rate]--;
            } else {
                index_rate++;
            }
        }
        return sortArray;
    }

    /**
     * 桶排序:
     * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
     * 桶排序 假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别使用别的排序算法排序。
     *
     * @param sortArray
     * @param bucketSize
     * @return
     */
    public static int[] bucketSort(int[] sortArray, int bucketSize) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        int min = sortArray[0];
        int max = sortArray[0];
        for (int i = 0; i < sortArray.length; i++) {
            if (sortArray[i] < min) {
                min = sortArray[i];
            }
            if (sortArray[i] > max) {
                max = sortArray[i];
            }
        }
        int bucketCount = (max - min) / bucketSize + 1;//桶的个数
        List<List<Integer>> bucket = new ArrayList<>();//每个桶是一个List
        for (int i = 0; i < bucketCount; i++) {//初始化桶
            bucket.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < sortArray.length; i++) {//将数据放入每一个桶中
            List<Integer> temp_bucket = bucket.get((sortArray[i] - min) / bucketSize);
            temp_bucket.add(sortArray[i]);
        }
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {//遍历所有桶分别进行排序，然后将数据拼接起来
            List<Integer> temp_bucket = bucket.get(i);
            if (temp_bucket.size() > 0) {
                int[] temp_bucket_array = new int[temp_bucket.size()];
                for (int j = 0; j < temp_bucket_array.length; j++) {
                    temp_bucket_array[j] = temp_bucket.get(j);
                }
                quickSort(temp_bucket_array, 0, temp_bucket_array.length - 1);//每个桶采用快速排序
                for (int k = 0; k < temp_bucket_array.length; k++) {
                    sortArray[index] = temp_bucket_array[k];
                    index++;
                }
            }
        }
        return sortArray;
    }

    /**
     * 基数排序：
     * 基数排序是分别对每一位（个，十，百...）进行排序，
     * 每次排序都在前一位已经排序的基础上进行，首先从最低位（个位）开始排序。
     *
     * @param sortArray
     * @return
     */
    public static int[] radixSort(int[] sortArray) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        int max = sortArray[0];
        for (int i = 0; i < sortArray.length; i++) {//取得待排数组中的最大值
            if (sortArray[i] > max) {
                max = sortArray[i];
            }
        }
        int maxDigit = 0;//最大值的位数
        while (max != 0) {//获取最大值的位数
            max = max / 10;
            maxDigit++;
        }
        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < 10; i++) {//每一位只可能是0-9，所以只需要建立10个桶
            bucket.add(new ArrayList<Integer>());
        }
        int mod = 10;
        int div = 1;
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {//从个位开始针对每一位的大小进行排序，直到最高位
            for (int j = 0; j < sortArray.length; j++) {
                int bucketIndex = (sortArray[j] % mod) / div;//获取数据映射的桶的索引，桶的索引就是数据在当前排序的位的值
                bucket.get(bucketIndex).add(sortArray[j]);
            }
            int index = 0;
            for (int m = 0; m < bucket.size(); m++) {//收集本次的排序结果并清空桶，方便下一次的排序
                for (int n = 0; n < bucket.get(m).size(); n++) {
                    sortArray[index++] = bucket.get(m).get(n);
                }
                bucket.get(m).clear();
            }
        }
        return sortArray;
    }

    /**
     * 交换数组中两个元素的值
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swapArrayElement(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 打印数组
     *
     * @param array
     */
    public static void PrintArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        int sortArray[] = new int[]{20, 5, 3, 9, 6, 7, 500, 36, 1, 36, 500, 20, -1};
        partition(sortArray, 0, sortArray.length - 1);
        //selectionSort(sortArray);
        //insertionSort(sortArray);
        //heerSort(sortArray);
        //sortArray = mergeSort(sortArray);
        //quickSort(sortArray, 0, sortArray.length-1);
        //heapSort(sortArray);
        //countingSort(sortArray);
        //bucketSort(sortArray, 50);
        //radixSort(sortArray);
        PrintArray(sortArray);
    }
}