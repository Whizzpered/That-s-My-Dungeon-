/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmd.environment.entities;

/**
 *
 * @author yew_mentzaki
 */
public class Gate extends Entity{

    public Gate(double x, double y) {
        super(x, y);
        phantom = true;
    }

    @Override
    public void move(double x, double y) {
        
    }
    
    
}
