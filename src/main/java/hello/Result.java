/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.Serializable;

/**
 *
 * @author mguillermo.ochoa
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -5054749880960511861L;
    private int addition;
    private int subtraction;
    private int multiplication;

    public int getAddition() { return addition; }
    public void setAddition(int addition) { this.addition = addition; }
    public int getSubtraction() { return subtraction; }
    public void setSubtraction(int subtraction) { this.subtraction = subtraction; }
    public int getMultiplication() { return multiplication; }
    public void setMultiplication(int multiplication) { this.multiplication = multiplication; }
}
