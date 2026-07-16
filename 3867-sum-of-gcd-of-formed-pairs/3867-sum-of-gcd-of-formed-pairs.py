from math import gcd

class Solution:
    def gcdSum(self, nums: List[int]) -> int:
        prefixGCD = []

        mx = float('-inf')

        for num in nums:
            mx = max(mx, num)
            prefixGCD.append(gcd(mx, num))

        prefixGCD.sort()

        i, j = 0, len(prefixGCD) - 1
        ans = 0

        while i < j:
            ans += gcd(prefixGCD[i], prefixGCD[j])
            i += 1
            j -= 1

        return ans