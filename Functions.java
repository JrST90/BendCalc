package joshs.bendcalculator;

public class Functions 
{
    public static double stainlessAllowanceCalc(double r, double t)
    {
        return (1.57 * r) + (0.71 * t); 
    }
    public static double regularAllowanceCalc(double r, double t)
    {
        return (1.57 * r) + (0.64 * t);
    }
    
   public static boolean isNumber(String n1, String n2, String n3)
   {
        for(char c : n1.toCharArray())
        {
            if((!Character.isDigit(c) && c != '.')) return false;
        }
        for(char c : n2.toCharArray())
        {
            if((!Character.isDigit(c) && c != '.')) return false;
        }
        for(char c : n3.toCharArray())
        {
            if((!Character.isDigit(c) && c != '.')) return false;
        }
        return true;
   }
}
