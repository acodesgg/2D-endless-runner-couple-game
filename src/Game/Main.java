/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import javax.swing.JFrame;
/**
 *
 * @author msi
 */
//window
//1st panel
//2nd panel
public class Main {
    private JFrame window;
    
    public Main(){
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window=new JFrame();
    window.setSize(600,800);
    window.setLocationRelativeTo(null);
    window.setTitle("FA");
    window.setResizable(false);
    window.setVisible(true);
    
    }
    public static void main(String[] args) {
        
        Main m=new Main();
    }
    
}
