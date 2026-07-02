import java.util.*;

class Solution {
    int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};

    private boolean canReach(int[][] dist,int limit){
        int n=dist.length;
        if(dist[0][0]<limit) return false;

        Queue<int[]> q=new LinkedList<>();
        boolean[][] vis=new boolean[n][n];
        q.offer(new int[]{0,0});
        vis[0][0]=true;

        while(!q.isEmpty()){
            int[] cur=q.poll();
            int r=cur[0],c=cur[1];

            if(r==n-1&&c==n-1) return true;

            for(int[] d:dirs){
                int nr=r+d[0];
                int nc=c+d[1];

                if(nr>=0&&nr<n&&nc>=0&&nc<n&&!vis[nr][nc]&&dist[nr][nc]>=limit){
                    vis[nr][nc]=true;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
        return false;
    }

    public int maximumSafenessFactor(List<List<Integer>> grid){
        int n=grid.size();
        int[][] dist=new int[n][n];

        for(int i=0;i<n;i++)
            Arrays.fill(dist[i],Integer.MAX_VALUE);

        Queue<int[]> q=new LinkedList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur=q.poll();
            int r=cur[0],c=cur[1];

            for(int[] d:dirs){
                int nr=r+d[0];
                int nc=c+d[1];

                if(nr>=0&&nr<n&&nc>=0&&nc<n&&dist[nr][nc]==Integer.MAX_VALUE){
                    dist[nr][nc]=dist[r][c]+1;
                    q.offer(new int[]{nr,nc});
                }
            }
        }

        int low=0,high=2*n,ans=0;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(canReach(dist,mid)){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }

        return ans;
    }
}