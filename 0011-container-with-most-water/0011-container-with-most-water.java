class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int n = height.length - 1;
        int a = 0;

        while (i < n) {
            a = Math.max(a, (n - i) * Math.min(height[i], height[n]));
            if (height[i] < height[n]) i++;
            else n--;
        }

        return a;
    }
}