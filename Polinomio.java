
/**
 * Utiliza los terminos para generar el polinomio y provee una función para evaluarlo
 * 
 * @author Paulina Vara
 * @version 20/03/2019
 */
import java.util.ArrayList;
public class Polinomio
{
    private Termino[] terminos;
    private int size = 0;
    
    public Polinomio (int grado)
    {
        int grad;
        grad = (grado >= 0 ? grado : 0);
        terminos = new Termino [grad];
    }
    public void agregaTermino (Termino term)
    {
        terminos [size] = term;
        size++;
    }
    public double evalua (double x)
    {
        double suma=0;
        Termino termEvaluar;
        for(int i=0 ; i<size; i++)//terminos.length
        {
            termEvaluar = new Termino (terminos[i].getCoeficiente(),terminos[i].getExponente());
            suma = suma + termEvaluar.evalua(x);
        }
        return suma;
    }
    public String toString ()
    {
        String polinomio = "";
        double coef;
        for(int i=0 ; i<size ; i++)
        {
            if(terminos[i].getCoeficiente() != 0)
            {
                if (i>0 && terminos[i].getCoeficiente()>=0)
                    polinomio = polinomio + " + ";
                else if (terminos[i].getCoeficiente()<0)
                    polinomio = polinomio + " - ";
                
                if (terminos[i].getCoeficiente()>0)
                    coef = terminos[i].getCoeficiente();
                else
                    coef = -terminos[i].getCoeficiente();
                
                if(terminos [i].getExponente()==0)
                    polinomio = polinomio + "" + coef;
                else if(terminos [i].getExponente()==1)
                    polinomio = polinomio + "" + (coef!=1 ? coef : "") + "x";
                else
                    polinomio = polinomio + "" + (coef!=1 ? coef : "") + "x ^ " + terminos [i].getExponente();
            }
        }
        return "y = " + polinomio;
    }
}
