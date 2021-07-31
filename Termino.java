
/**
 * La clase genera un nuevo término recibiendo el coeficiente y el exponente
 * 
 * @author Paulina Vara
 * @version 13/03/2019
 */
public class Termino
{
    private double coeficiente;
    private int exponente;
    
    public Termino (double coef, int exp)
    {
        coeficiente = coef;
        exponente = exp;
    }
    public double evalua (double x)
    {
        return (coeficiente*Math.pow(x,exponente));
    }
    public int getExponente ()
    {
        return exponente;
    }
    public double getCoeficiente ()
    {
        return coeficiente;
    }
}
