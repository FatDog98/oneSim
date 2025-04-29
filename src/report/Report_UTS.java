package report;

import core.DTNHost;
import core.SimScenario;
import routing.DecisionEngineRouter;
import routing.MessageRouter;
import routing.RoutingDecisionEngine;
import routing.community.CentralityGlobal;
import routing.community.CommunityDetectionEngine;

import java.util.*;

public class Report_UTS extends Report{
    public Report_UTS() {
        init();
    }
    public void done() {
        List<DTNHost> nodes = SimScenario.getInstance().getHosts();
        Map<DTNHost, List<Integer>> arrayGlobal = new HashMap<DTNHost, List<Integer>>();
        for (DTNHost h : nodes) {
            MessageRouter r = h.getRouter();
            if (!(r instanceof DecisionEngineRouter)) {
                continue;
            }
            RoutingDecisionEngine de = ((DecisionEngineRouter) r).getDecisionEngine();
            if (!(de instanceof CentralityGlobal)) {
                continue;
            }

            CentralityGlobal ctd = (CentralityGlobal) de;

            int [] global = ctd.getGlobal();

            List<Integer> globalArray = new ArrayList<Integer>();

            for (int cent : global)globalArray.add(cent);

            arrayGlobal.put(h, globalArray);

        }
        for (Map.Entry<DTNHost, List<Integer>> i : arrayGlobal.entrySet() ) {
            String simpanKey = String.valueOf(i.getKey()+","+i.getValue());
            write(simpanKey);
            }
        super.done();
        }
    }
