/**
 * Genera una "tabla" de valores para la ecuación evaluada en un intevalo dado
 * 
 * @author Paulina Vara
 * @version 20/03/2019
 */

import java.util.Vector;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class PuntosEcuacion extends Canvas implements Runnable
{
    private Vector<Punto> puntos;
    private Polinomio poli;
    private Color ch_color;
    private Thread hilo;
    
    public PuntosEcuacion (Polinomio poli, double linf, double lsup, double inc, Color color)
    {
        ch_color=new Color(color.getRed(),color.getGreen(),color.getBlue());
        this.poli = poli;
        if(poli != null)
        {
            int tam;
            tam = (linf < lsup) ? (int)((lsup-linf)/inc) : 0;
            puntos = new Vector (tam);
            if(linf<lsup)
            {
                for (double i=linf; i<=lsup; i+=inc)
                    puntos.addElement(new Punto (i,poli.evalua(i)));
            }
            else
                puntos.addElement(new Punto (linf,poli.evalua(linf)));
            
        }
        start();
    }
    
    public void guardar ()
    {
        PrintWriter salida;
        try
        {
            salida = new PrintWriter (new BufferedWriter (new FileWriter("coordenadas.csv")));
            String punt_cad;
            salida.println("Coordenadas de la Funcion:  " + poli.toString());
            salida.println("x,y");
            for (int i=0;i<puntos.size();i++)
            {
                punt_cad = puntos.elementAt(i).getX() + "," + puntos.elementAt(i).getY();
                salida.println(punt_cad);
            }
            salida.close();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
    }
    
    public void start()
    {
        if(hilo == null)
        {
           hilo=new Thread(this);
           hilo.start();
        }
    }
     
    public void stop()
    {
        if(hilo!=null)
        {
           hilo.stop();
           hilo=null;
        }
    }
    
    public void run()
    {
        
    }
    
    public Vector getPuntosEcuacion()
    {
        return new Vector (puntos);
    }
    
    public Punto getPunto (int num)
    {
        return new Punto(puntos.elementAt(num).getX(), puntos.elementAt(num).getY());
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.translate(350,350);
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Float (-350,-350, 700, 700));
        
        for(int i = -350; i<=350 ; i+=25)
        {
            g2d.setColor(Color.BLACK);
            g2d.draw(new Line2D.Float (-350,i, 350, i));
            g2d.draw(new Line2D.Float (i,-350, i, 350));
        }
        
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.draw(new Line2D.Float (-350,0, 350, 0));
        g2d.draw(new Line2D.Float (0,-350,0,350));
        
        g2d.setColor(new Color (0,70,140));
        Font fuente = new Font("Times New Roman", Font.ITALIC+ Font.BOLD, 30);
        g.setFont(fuente);
        g.drawString("x", 310, 40);
        g.drawString("y", -40, -310);
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Float (-349,301, 697, 70));
        //----------------------------------------------------------------
        if(poli != null)
        {
            g2d.setColor(ch_color);
            g2d.setStroke(new BasicStroke(3.0f));
            
            GeneralPath polilinea = null;
            polilinea = new GeneralPath(GeneralPath.WIND_EVEN_ODD, puntos.size());
        
            g2d.setColor(ch_color);
            fuente = new Font("Arial", Font.ITALIC+ Font.BOLD, 30);
            g.setFont(fuente);
            g.drawString(poli.toString(), -300, 330);
            //Valida que no empiece muy lejos de lo que se muestra en la gráfica                                        
            int j=0;                        
            while (((-25*(double)puntos.elementAt(j).getY()>300) || (-25*(double)puntos.elementAt(j).getY()<-350))) 
                j++;
                
            if(j!=0)
                j-=1;
                
            polilinea.moveTo(25*(double)puntos.elementAt(j).getX(),-25*(double)puntos.elementAt(j).getY());
            for(int i=j+1;i<puntos.size();i++)
            {
                polilinea.lineTo(25*(double)puntos.elementAt(i).getX(),-25*(double)puntos.elementAt(i).getY());
                g2d.draw(polilinea);
                try {
                    hilo.sleep(50);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            
            g2d.setColor(Color.WHITE);
            g2d.fill(new Rectangle2D.Float (-349,301, 699, 70));
        
            g2d.setColor(ch_color);
            fuente = new Font("Arial", Font.ITALIC+ Font.BOLD, 30);
            g.setFont(fuente);
            g.drawString(poli.toString(), -300, 330);
        }
        stop();
    }
}
