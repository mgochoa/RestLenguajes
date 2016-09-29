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
public class Request implements Serializable {
    private static final long serialVersionUID = 1513207428686438208L;
    private int left;
    private int right;
    public int getLeft() {return left;}
    public void setLeft(int left) {this.left = left;}
    public int getRight() {return right;}
    public void setRight(int right) {this.right = right;}
}
