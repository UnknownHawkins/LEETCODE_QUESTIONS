class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[int]:
        
        # map original index of some val to index in sorted array of that val
        sortedIdx = sorted(range(n), key = lambda i:nums[i])

        idxMap = {}

        for newIdx, origIdx in enumerate(sortedIdx):
            idxMap[origIdx] = newIdx

        nums.sort()

        # for each nums[i] find the rightmost j s.t |nums[i] - nums[j]| <= maxDiff
        # this is essentially the "immediate parent" base case for a 
        # binary lifting problem
        maxReach = [-1] * n

        j = 0
        for i in range(n):
            while j < n and abs(nums[i] - nums[j]) <= maxDiff:
                j += 1

            maxReach[i] = j - 1

        # build binary lifting array
        # up[i][k] = furthest pos reached starting at node i with 2^k jumps

        # LOG is the most jumps we could possibly need to reach the root
        # from the very bottom node (i.e. the worst case when our tree is entirely vertical)
        # it just gives us an upper bound for our array dimensions and for loops
        LOG = max(1, (n - 1).bit_length())

        A = [[-1] * LOG for _ in range(n)]

        # set up all A[i][0] as base case looking at one jump
        for i in range(n):
            A[i][0] = maxReach[i]

        # populate the rest of the array
        for k in range(1, LOG):
            for i in range(n):
                A[i][k] = A[A[i][k - 1]][k - 1]


        # helper to calculate min jumps needed to get from 
        # index a to index b
        def minJumps(a, b):

            # trivial case where start and end are the same
            # no jumps needed since we start at our destination
            if a >= b:
                return 0

            ans = 0
            cur = a

            for k in range(LOG - 1, -1, -1):
                if A[cur][k] < b:
                    cur = A[cur][k]
                    ans += 2 ** k

            return ans + 1 if maxReach[cur] >= b else -1

        res= []

        for u, v in queries:
            a, b = idxMap[u], idxMap[v]

            a, b = min(a, b), max(a, b)

            res.append(minJumps(a, b))

        return res            