public class SortingAlgorithm {
    private int[] array;
    
    public SortingAlgorithm() {
        this.array = new int[0];
    }
    
    public void setArray(int[] array) {
        this.array = array.clone();
    }
    
    public int[] getArray() {
        return array.clone();
    }
    
    public String bubbleSort() {
    if (array == null || array.length == 0) {
        return "Array kosong";
    }
    
    StringBuilder steps = new StringBuilder();
    steps.append("Bubble Sort Steps:\n\n");
    int[] arr = array.clone();
    
    for (int i = 0; i < arr.length - 1; i++) {
        boolean swapped = false;
        
        // Lakukan iterasi dan swap
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap elements
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }
        
        // Tambahkan langkah setelah satu iterasi lengkap
        if (swapped) {
            steps.append("Step ").append(i + 1).append(": ")
                 .append(arrayToString(arr)).append("\n");
        }
        
        // Jika tidak ada swap, array sudah terurut
        if (!swapped) break;
    }
    
    steps.append("\nHasil akhir: ").append(arrayToString(arr));
    return steps.toString();
}
    
    public String selectionSort() {
        if (array == null || array.length == 0) {
            return "Array kosong";
        }
        
        StringBuilder steps = new StringBuilder();
        steps.append("Selection Sort Steps:\n\n");
        int[] arr = array.clone();
        
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            
            if (minIdx != i) {
                // Swap elements
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                
                // Add step to output
                steps.append("Step ").append(i + 1).append(": ")
                     .append(arrayToString(arr)).append("\n");
            }
        }
        
        steps.append("\nHasil akhir: ").append(arrayToString(arr));
        return steps.toString();
    }
    
    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}