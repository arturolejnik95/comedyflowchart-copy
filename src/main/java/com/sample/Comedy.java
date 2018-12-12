package com.sample;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class Comedy {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	
        	new Comedy().init(kContainer, true);


        } catch (Throwable t) {
            t.printStackTrace();
        }
        

    }
    
    
    public void init(KieContainer kc, boolean exitOnClose) {
        ComedyUI ui = new ComedyUI(kc);
        ui.createAndShowGUI(exitOnClose);
        ui.runRules();
    }

    
    public static class ComedyUI extends JPanel {

        private static final long serialVersionUID = 510l;

        private JTextArea        output;

        KieSession ks;
        KieContainer kc;

        public ComedyUI(KieContainer kContainer) {
            super( new BorderLayout() );
            this.kc = kContainer;
            this.ks = kc.newKieSession("ksession-rules");
            
            //GUI-START            
            JSplitPane splitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
            add( splitPane,
                 BorderLayout.CENTER );
            JPanel bottomHalf = new JPanel( new BorderLayout() );
            bottomHalf.setMinimumSize( new Dimension( 400,
                                                      50 ) );
            bottomHalf.setPreferredSize( new Dimension( 1000,
                                                        300 ) );
            splitPane.add( bottomHalf );

            JPanel checkoutPane = new JPanel();
            JButton button = new JButton( "Reset" );
            button.setVerticalTextPosition( AbstractButton.CENTER );
            button.setHorizontalTextPosition( AbstractButton.TRAILING );
            //attach handler to assert items into working memory
            button.addMouseListener( new ResetButtonHandler() );
            button.setActionCommand( "reset" );
            checkoutPane.add( button );
            bottomHalf.add( checkoutPane,
                            BorderLayout.NORTH );
            output = new JTextArea( 1,
                                    10 );
            output.setEditable( false );
            JScrollPane outputPane = new JScrollPane( output,
                                                      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                                      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            bottomHalf.add( outputPane,
                            BorderLayout.CENTER );      
            //GUI-END
            
            

        }
        
        /**
         * Create and show the GUI
         */
        public void createAndShowGUI(boolean exitOnClose) {
            //Create and set up the window.
            JFrame frame = new JFrame( "Comedy" );
            frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);

            setOpaque( true );
            frame.setContentPane( this );

            //Display the window.
            frame.pack();
            frame.setLocationRelativeTo(null); // Center in screen
            frame.setVisible( true );
        }
        
        /**
         * Resets the shopping cart, allowing the user to begin again.
         */
        private class ResetButtonHandler extends MouseAdapter {
            public void mouseReleased(MouseEvent e) {
                output.setText( null );
                
                
                runRules();
                
                
                
                System.out.println( "------ Reset ------" );
            }
        }
        
        public void runRules() {
        	ks.dispose();
        	
        	ks = kc.newKieSession("ksession-rules");
            ks.setGlobal( "textArea",
                    this.output );

            ks.fireAllRules();
        }

    }
        



}
