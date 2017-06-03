package com.tutor.controller.preprocessor;

import com.tutor.model.Line;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Madhavi Ruwandika on 6/3/2017.
 */
public class numberLinePreProcessor {


    public static void main(String[] args) {

        //Boostrap the CDI container, in this case WELD
        System.out.println("============================opening================================");
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "NumberLine");
        ksession.insert( new Line("vertical") );
        ksession.fireAllRules();
        System.out.println("============================Closing==================================");

    }

}
