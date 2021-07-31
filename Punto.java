
/**
 * Muestra y modifica los puntos obtenidos al evaluar la ecuación
 * 
 * @author Paulina Vara
 * @version 13/03/2019
 */
public class Punto <E>//MODIFICAR A GENÉRICO...
{
    private E x, y;
    
    public Punto (E x, E y)
    {
        this.x = x;
        this.y = y;
    }
    public E getX()
    {
        return x;
    }
    public void setX(E x)
    {
        this.x=x;
    }
    public E getY()
    {
        return y;
    }
    public void setY(E y)
    {
        this.y=y;
    }
}
