package com.alma.pay2bid.gui.listeners;

import com.alma.pay2bid.client.IClient;
import com.alma.pay2bid.gui.AuctionView;
import com.alma.pay2bid.server.IServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * An ActionListener called to raise the bid of an item
 * @author Alexis Giraudet
 * @author Arnaud Grall
 * @author Thomas Minier
 */
public class RaiseBidButtonListener implements ActionListener {
    private IClient client;
    private IServer server;
    private AuctionView auctionView;
    private JTextField bidField;
    private JLabel statusLabel;

    public RaiseBidButtonListener(IClient client, IServer server, AuctionView gui, JLabel statusLabel) {
        this.client = client;
        this.server = server;
        auctionView = gui;
        this.bidField = gui.getAuctionBid();
        this.statusLabel = statusLabel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();        
        if("raiseBid".equals(command)){        	
            statusLabel.setText("New bid sent.");
            try {
                server.raiseBid(client, Integer.valueOf(bidField.getText()));
                auctionView.disable();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
        	if("quickRaise10".equals(command))  {                		
        		statusLabel.setText("New bid sent +10%");
        		 try {
        			 int bidRaise = (Integer.valueOf(auctionView.getAuctionPriceValue().getText()));
        			 bidRaise = (int) Math.round(bidRaise * 1.10);        			 
                     server.raiseBid(client, bidRaise);
                     auctionView.disable();
                 } catch (RemoteException e) {
                     e.printStackTrace();
                 }        	
        	} else {
        	if("quickRaise25".equals(command))  {          		
        		statusLabel.setText("New bid sent +25%");
        		 try {        			 
        			 int bidRaise = (Integer.valueOf(auctionView.getAuctionPriceValue().getText()));
        			 bidRaise = (int) Math.round(bidRaise * 1.25);        			 
                     server.raiseBid(client, bidRaise);
                     auctionView.disable();
                 } catch (RemoteException e) {
                     e.printStackTrace();
                 }
        	}
        	}
        }
    }

	public AuctionView getAuctionView() {
		return auctionView;
	}

	public void setAuctionView(AuctionView auctionView) {
		this.auctionView = auctionView;
	}
    
    
    
}
