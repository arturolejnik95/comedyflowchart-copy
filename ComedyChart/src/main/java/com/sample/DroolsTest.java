package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static class PytanieIOdpowiedz {

        private String pytanie;
        private String odpowiedz;

        public PytanieIOdpowiedz(String pytanie){
        	this.pytanie = pytanie;
        	this.odpowiedz = "";
        }
        
        public PytanieIOdpowiedz(String pytanie, String odpowiedz){
        	this.pytanie = pytanie;
        	this.odpowiedz = odpowiedz;
        }
        
        public String getPytanie() {
            return this.pytanie;
        }

        public void setPytanie(String pytanie) {
            this.pytanie = pytanie;
        }
        
        public String getOdpowiedz() {
            return this.odpowiedz;
        }

        public void setOdpowiedz(String odpowiedz) {
            this.odpowiedz = odpowiedz;
        }


    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
