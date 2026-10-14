package com.portfolio.menuplanner.service;
import com.google.ortools.Loader;
import com.google.ortools.sat.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MenuPlannerService {
 public Map<String,Object> plan(List<Map<String,Object>> dishes){
   Loader.loadNativeLibraries();
   int n=Math.min(dishes.size(),120);
   if(n==0) return Map.of("weeks",4,"days_per_week",7,"assignments",List.of());
   CpModel model=new CpModel();
   BoolVar[][] x=new BoolVar[4][n];
   for(int w=0;w<4;w++) for(int i=0;i<n;i++) x[w][i]=model.newBoolVar("w"+w+"_d"+i);
   for(int w=0;w<4;w++) model.addEquality(
      LinearExpr.sum(Arrays.copyOf(x[w],n)), 7);
   for(int i=0;i<n;i++) model.addLessOrEqual(LinearExpr.sum(
      new IntVar[]{x[0][i],x[1][i],x[2][i],x[3][i]}), 2);
   CpSolver solver=new CpSolver();
   solver.getParameters().setMaxTimeInSeconds(3);
   CpSolverStatus status=solver.solve(model);
   List<Map<String,Object>> out=new ArrayList<>();
   if(status==CpSolverStatus.OPTIMAL || status==CpSolverStatus.FEASIBLE){
     for(int w=0;w<4;w++){
       List<Integer> ids=new ArrayList<>();
       for(int i=0;i<n;i++) if(solver.booleanValue(x[w][i])) ids.add(i+1);
       out.add(Map.of("week",w+1,"dish_ids",ids));
     }
   }
   return Map.of("weeks",4,"status",status.toString(),"assignments",out);
 }
}
