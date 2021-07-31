/**
 * El menú pide los datos para generar la gráfica
 * 
 * @author Paulina Vara
 * @version 08/05/2019
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame
{
    private JButton b_graf, b_addPoli, b_addTer, b_color, b_setLim, b_guardarPuntos;
    private JLabel labGrad, labCoef, labExp, labLinf, labLsup, labInc, labPrinc;
    private JTextField tf_coef, tf_exp, tf_grado, tf_linf, tf_lsup, tf_inc;
    private JColorChooser colorCh;
    private int grado_poli = 0, num_term = 0;
    private Polinomio polinomio;
    private Color color;
    private double linf, lsup, inc;
    private PuntosEcuacion puntosEcuacion;
    private JPanel pa_graf, pa_menu;
    
    public Menu() 
    {
        super("Programa Graficador");
        setLayout(null);
        color = new Color (182,5,18);
        
        pa_graf = new JPanel (new BorderLayout());
        puntosEcuacion = new PuntosEcuacion (polinomio, linf, lsup, inc,color);
        pa_graf.add(puntosEcuacion, "Center");
        pa_graf.setBounds(380,0,700,700);
        add(pa_graf);
       
        pa_menu = new JPanel (null);
        
        labPrinc = new JLabel ("Gráfico de una ecuación");
        labPrinc.setFont(new java.awt.Font("Arial", 0, 27));
        labPrinc.setBounds(25,20,400,30);
        pa_menu.add(labPrinc);
        
        //Crear Polinomio
        labGrad=new JLabel("Grado del polinomio:");
        labGrad.setFont(new java.awt.Font("Arial", 0, 16));
        labGrad.setBounds(25,60,200,30);
        pa_menu.add(labGrad);
        
        tf_grado=new JTextField("");
        tf_grado.setBounds(210,63,150,25);
        tf_grado.setEnabled(true);
        pa_menu.add(tf_grado);
        
        b_addPoli=new JButton("CREAR NUEVO POLINOMIO");
        b_addPoli.setBackground(new Color(160,207,245));
        b_addPoli.setForeground(Color.BLACK);
        b_addPoli.setBounds(25,105,335,25);
        b_addPoli.addActionListener(new BotonAddPolinomio());
        pa_menu.add(b_addPoli);
        //----------------------------------------------------
        //Agregar Término
        labCoef=new JLabel("Coeficiente del termino:");
        labCoef.setFont(new java.awt.Font("Arial", 0, 16));
        labCoef.setBounds(25,170,200,30);
        pa_menu.add(labCoef);
        
        labExp=new JLabel("Exponente del termino:");
        labExp.setFont(new java.awt.Font("Arial", 0, 16));
        labExp.setBounds(25,210,200,30);
        pa_menu.add(labExp);
        
        tf_coef=new JTextField("");
        tf_coef.setEnabled(false);
        tf_coef.setBounds(210,173,150,25);
        pa_menu.add(tf_coef);
        
        tf_exp=new JTextField("");
        tf_exp.setBounds(210,213,150,25);
        tf_exp.setEnabled(false);
        pa_menu.add(tf_exp);
        
        b_addTer=new JButton("AGREGAR TERMINO");
        b_addTer.setBackground(new Color(160,207,245));
        b_addTer.setForeground(Color.BLACK);
        b_addTer.setBounds(25,255,335,25);
        b_addTer.addActionListener(new BotonAddTermino());
        b_addTer.setEnabled(false);
        pa_menu.add(b_addTer);
        //----------------------------------------------------
        //Botón Para Guardar Puntos en Archivo puntos.txt
        b_guardarPuntos = new JButton("GUARDAR PUNTOS EN ARCHIVO COORDENADAS.CSV");
        b_guardarPuntos.setBackground(new Color(160,207,245));
        b_guardarPuntos.setForeground(Color.BLACK);
        b_guardarPuntos.setBounds(25,630,335,35);
        b_guardarPuntos.addActionListener(new BotonWrite());
        b_guardarPuntos.setEnabled(false);
        pa_menu.add(b_guardarPuntos);
        //----------------------------------------------------
        //Fijar Condiciones Para Graficar
        labLinf = new JLabel ("Límite inferior de gráfico:");
        labLinf.setFont(new java.awt.Font("Arial",0,16));
        labLinf.setBounds(25,325,200,30);
        pa_menu.add(labLinf);
        
        labLsup = new JLabel ("Límite superior de gráfico:");
        labLsup.setFont(new java.awt.Font("Arial",0,16));
        labLsup.setBounds(25,365,200,30);
        pa_menu.add(labLsup);
        
        labInc = new JLabel ("Incremento de gráfico:");
        labInc.setFont(new java.awt.Font("Arial",0,16));
        labInc.setBounds(25,405,200,30);
        pa_menu.add(labInc);
        
        tf_linf = new JTextField("");
        tf_linf.setEnabled(false);
        tf_linf.setBounds(210,328,150,25);
        pa_menu.add(tf_linf);
        
        tf_lsup = new JTextField("");
        tf_lsup.setEnabled(false);
        tf_lsup.setBounds(210,368,150,25);
        pa_menu.add(tf_lsup);
        
        tf_inc = new JTextField("");
        tf_inc.setEnabled(false);
        tf_inc.setBounds(210,408,150,25);
        pa_menu.add(tf_inc);
        
        b_setLim = new JButton ("FIJAR CONDICIONES");
        b_setLim.setBackground(new Color(160,207,245));
        b_setLim.setForeground(Color.BLACK);
        b_setLim.setBounds(25,450,335,25);
        b_setLim.addActionListener(new BotonSetLimites());
        b_setLim.setEnabled(false);
        pa_menu.add(b_setLim);
        //----------------------------------------------------
        //Cambiar Color del Gráfico
        b_color=new JButton("ELEGIR COLOR PARA GRAFICAR");
        b_color.setBackground(new Color(160,207,245));
        b_color.setForeground(Color.BLACK);
        b_color.setBounds(25,500,335,35);
        b_color.addActionListener(new BotonColor());//Se registra como generador de evento action
        b_color.setEnabled(false);
        pa_menu.add(b_color);
        //Graficar
        b_graf=new JButton("GRAFICAR");
        b_graf.setBackground(new Color(160,207,245));
        b_graf.setForeground(Color.BLACK);
        b_graf.setFont(new java.awt.Font("Arial",0,22));
        b_graf.setBounds(25,542,335,80);
        b_graf.addActionListener(new BotonGraficar());//Se registra como generador de evento action
        b_graf.setEnabled(false);
        pa_menu.add(b_graf);
        
        pa_menu.setBounds(0,0,380,700);
        add (pa_menu);
        
        addWindowListener(new CW());
        
        setSize(1085,730);
        setResizable(false);
        setVisible(true);
        //puntosEcuacion.start();
    }
    
    public class LimitsException extends ArithmeticException
    {
        public LimitsException ()
        {
            super("Los límites son inválidos");
        }
    }
    
    public class InvalidInputException extends ArithmeticException
    {
        public InvalidInputException ()
        {
            super("Ingresaste un dato inválido");
        }
    }
    
    public class IncrementException extends ArithmeticException
    {
        public IncrementException ()
        {
            super("El incremento es inválido");
        }
    }
    
    public class BotonSetLimites implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                fijarCondiciones();
            }catch(LimitsException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }catch(IncrementException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        public void fijarCondiciones()
        {
            double t_inf = 0, t_sup = 0, t_inc = 0;
            boolean valida = true;
            try{
                t_inf = Double.parseDouble(tf_linf.getText());
                t_sup = Double.parseDouble(tf_lsup.getText());
                t_inc = Double.parseDouble(tf_inc.getText());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Datos inválidos para fijar condiciones","Error", JOptionPane.ERROR_MESSAGE);
                valida = false;
            }
            if (valida)
            {
                if(t_sup <= t_inf)
                    throw new LimitsException();
                else if (t_inc <= 0)
                    throw new IncrementException();
                else
                {
                    linf = Double.parseDouble(tf_linf.getText());
                    lsup = Double.parseDouble(tf_lsup.getText());
                    inc = Double.parseDouble(tf_inc.getText());
                    b_graf.setEnabled(true);
                    b_guardarPuntos.setEnabled(true);
                }
            }
        }
    }
    
    public class BotonWrite implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            puntosEcuacion = new PuntosEcuacion(polinomio,linf,lsup,inc,color);
            puntosEcuacion.guardar();
            JOptionPane.showMessageDialog(null, "Los puntos se han guardado con éxito en el archivo \"coordenadas.csv\"", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public class BotonColor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Color tempColor;
            tempColor = color;
            colorCh = new JColorChooser ();
            color = colorCh.showDialog(null, "Seleccione un color para el gráfico", tempColor);
            color = (color != null ? color : tempColor);
        }
    }
    
    public class BotonGraficar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            pa_graf.removeAll();
            
            puntosEcuacion = new PuntosEcuacion(polinomio,linf,lsup,inc,color);
            pa_graf.add(puntosEcuacion, "Center");
            //pa_graf.repaint();
            //pa_graf.setVisible(true);
            pa_graf.updateUI();
            //add(pa_graf);
        }
    }
    
    public class BotonAddPolinomio implements ActionListener
    {
        private boolean valida = true;
        public void actionPerformed(ActionEvent e)
        {
            int entra = 0;
            if (polinomio != null)
            {
                entra = JOptionPane.showConfirmDialog(null, "Se eliminará el polinomio actual, ¿Desea continuar?",
                "Permiso para continuar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }
            if (entra==0)
            {
                try{
                    addPolinomio();
                }catch (InvalidInputException ex){
                    if(valida)
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                    valida = false;
                }
                if (valida)
                {
                linf = lsup = 0;
                puntosEcuacion = new PuntosEcuacion(polinomio,linf,lsup,inc,color);
                pa_graf.removeAll();
                pa_graf.add(puntosEcuacion, "Center");
                pa_graf.setBounds(380,0,700,700);
                pa_graf.updateUI();
            }
            }
        }
        private void addPolinomio ()
        {
           int grado=0;
           valida=true;
           try{
               grado = (int)Integer.parseInt(tf_grado.getText());
           }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Ingresaste un dato inválido","Error", JOptionPane.ERROR_MESSAGE);
                valida = false;
           }
           if(grado <= 0)
                throw new InvalidInputException();
           else
           {
                if (valida)
                {
                    num_term = 0;
                    grado_poli = (grado > 0 ? grado : 0);
                    polinomio = new Polinomio (grado_poli);
                    tf_linf.setText("");
                    tf_lsup.setText("");
                    tf_inc.setText("");
                    b_color.setEnabled(false);
                    b_graf.setEnabled(false);
                    b_guardarPuntos.setEnabled(false);
                    tf_exp.setEnabled(true);
                    tf_coef.setEnabled(true);
                    b_addTer.setEnabled(true);
                    b_setLim.setEnabled(false);
                    tf_linf.setEnabled(false);
                    tf_lsup.setEnabled(false);
                    tf_inc.setEnabled(false);
                }
           }
        }
    }
    
    public class BotonAddTermino implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            double t_coe;
            int t_exp;
            boolean valida = true;
            try{
                t_coe = Double.parseDouble( tf_coef.getText() );
                t_exp = Integer.parseInt( tf_exp.getText() );
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Datos inválidos para añadir término","Error", JOptionPane.ERROR_MESSAGE);
                valida = false;
            }
            if(valida)
            {
                Termino termi;
                termi = new Termino (Double.parseDouble( tf_coef.getText() ), Integer.parseInt( tf_exp.getText() ));
                tf_coef.setText("");
                tf_exp.setText("");
                polinomio.agregaTermino(termi);
                b_color.setEnabled(true);
                b_setLim.setEnabled(true);
                tf_linf.setEnabled(true);
                tf_lsup.setEnabled(true);
                tf_inc.setEnabled(true);
                num_term++;
                if(num_term == grado_poli)
                {
                    tf_coef.setEnabled(false);
                    tf_exp.setEnabled(false);
                    b_addTer.setEnabled(false);
                }
            }
        }
    }
    
    private class CW extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false); //Sustituye al viejo método hide()
            dispose();
        }
    }
    
}
