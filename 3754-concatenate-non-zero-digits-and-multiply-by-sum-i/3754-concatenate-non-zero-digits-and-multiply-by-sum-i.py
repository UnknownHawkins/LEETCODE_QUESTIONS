class Solution:
    def sumAndMultiply(self, n: int) -> int:
        s = sum(map(int, str(n)))
        x = int((''.join(c for c in str(n) if c != '0')[::1]) or 0)
        return s * x