import java.util.*;

class Solution{
public boolean findSafeWalk(List<List<Integer>> grid,int health){
int m=grid.size(),n=grid.get(0).size();
int[][] dist=new int[m][n];
for(int[] row:dist) Arrays.fill(row,Integer.MIN_VALUE);
Queue<int[]> q=new LinkedList<>();
q.offer(new int[]{health-grid.get(0).get(0),0,0});
dist[0][0]=health-grid.get(0).get(0);
int[] dx={0,0,1,-1};
int[] dy={1,-1,0,0};

while(!q.isEmpty()){
int[] cur=q.poll();
int h=cur[0],i=cur[1],j=cur[2];
if(h<dist[i][j]) continue;

for(int k=0;k<4;k++){
int ni=i+dx[k],nj=j+dy[k];
if(ni>=0&&ni<m&&nj>=0&&nj<n){
int nh=h-grid.get(ni).get(nj);
if(nh>dist[ni][nj]){
dist[ni][nj]=nh;
q.offer(new int[]{nh,ni,nj});
}
}
}
}
return dist[m-1][n-1]>=1;
}
}