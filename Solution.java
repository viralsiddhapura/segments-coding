import java.util.*;

public class Solution {
    public int[] changeSegmentColors(String[] operations) {
        String[] firstLine = operations[0].split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        // Build graph (adjacency list)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            String[] edge = operations[i].split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // parent map to reconstruct path
        Map<Integer, Integer> parent = new HashMap<>();
        // depth map
        Map<Integer, Integer> depth = new HashMap<>();

        // Preprocessing with DFS
        dfs(1, -1, 0, graph, parent, depth);

        // Track edge colors: key is (min(u,v), max(u,v)) => true if black, false if red
        Map<String, Boolean> edgeColor = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for (int i = n; i < operations.length; i++) {
            String[] parts = operations[i].split(" ");
            int type = Integer.parseInt(parts[0]);
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);

            List<int[]> pathEdges = getPathEdges(a, b, parent, depth);

            if (type == 1) {
                // First convert all edges connected to each node on path to red
                Set<Integer> pathNodes = getPathNodes(a, b, parent, depth);
                for (int node : pathNodes) {
                    for (int neighbor : graph.get(node)) {
                        String key = getKey(node, neighbor);
                        edgeColor.put(key, false); // set to red
                    }
                }
                // Then convert path edges to black
                for (int[] edge : pathEdges) {
                    String key = getKey(edge[0], edge[1]);
                    edgeColor.put(key, true);
                }
            } else if (type == 2) {
                int blackCount = 0;
                for (int[] edge : pathEdges) {
                    String key = getKey(edge[0], edge[1]);
                    if (edgeColor.getOrDefault(key, false)) {
                        blackCount++;
                    }
                }
                result.add(blackCount);
            }
        }

        // Convert result to int[]
        int[] resArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArr[i] = result.get(i);
        }
        return resArr;
    }

    private void dfs(int node, int par, int d, Map<Integer, List<Integer>> graph,
                     Map<Integer, Integer> parent, Map<Integer, Integer> depth) {
        parent.put(node, par);
        depth.put(node, d);
        for (int neighbor : graph.get(node)) {
            if (neighbor != par) {
                dfs(neighbor, node, d + 1, graph, parent, depth);
            }
        }
    }

    private List<int[]> getPathEdges(int a, int b, Map<Integer, Integer> parent, Map<Integer, Integer> depth) {
        List<int[]> pathEdges = new ArrayList<>();
        while (a != b) {
            if (depth.get(a) > depth.get(b)) {
                pathEdges.add(new int[]{a, parent.get(a)});
                a = parent.get(a);
            } else {
                pathEdges.add(new int[]{b, parent.get(b)});
                b = parent.get(b);
            }
        }
        return pathEdges;
    }

    private Set<Integer> getPathNodes(int a, int b, Map<Integer, Integer> parent, Map<Integer, Integer> depth) {
        Set<Integer> pathNodes = new HashSet<>();
        while (a != b) {
            pathNodes.add(a);
            pathNodes.add(b);
            if (depth.get(a) > depth.get(b)) {
                a = parent.get(a);
            } else {
                b = parent.get(b);
            }
        }
        pathNodes.add(a); // add the common ancestor
        return pathNodes;
    }

    private String getKey(int u, int v) {
        return u < v ? u + "," + v : v + "," + u;
    }
}
