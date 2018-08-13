/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irregularexpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class IrregularExpression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        for (int i=0;i<10;i++) {
            Expression exp=new Expression(sc.next());
            for (int j=0;j<5;j++) {
                if (exp.match(sc.next())) System.out.print("true ");
                else System.out.print("false ");
            }
            System.out.println();
        }
    }
    
}

class Expression {
    ArrayList<Pattern> patterns=new ArrayList();
    public Expression(String sExp) {
        int i=0;
        while (i<sExp.length()) {
            int idx1=sExp.indexOf('[',i);
            
            if (idx1>=0) {
                if (idx1>0) {
                    Pattern pt=new Pattern1(sExp.substring(i,idx1));
                    patterns.add(pt);
                }
                int idx2=sExp.indexOf(']', idx1);
                if (idx2<0) idx2=sExp.length();
                
                i=idx2+1;
                Pattern pt=new Pattern2(sExp.substring(idx1+1,idx2));
                patterns.add(pt);
            } else {
                Pattern pt=new Pattern1(sExp.substring(i));
                patterns.add(pt);
                break;
            }
        }
    }
    
    public boolean match(String word) {
        int i=0;
        for (Pattern pt:this.patterns) {
            if (i+pt.length>word.length()) return false;
            String w=word.substring(i,pt.length+i);
            if (!pt.match(w)) return false;
            i+=pt.length;
            
        }
        if (i<word.length()) return false;
        return true;
    }
}

abstract class Pattern {
    
    String sPattern;
    public int length;
    abstract public boolean match(String word);
    
    public Pattern(String s) {
        this.sPattern=s;        
    }
}

class Pattern1 extends Pattern {
    public Pattern1(String s) {
        super(s);
        this.length=s.length();
    }

    @Override
    public boolean match(String word) {
        return (word.equals(sPattern));
    }
}

class Pattern2 extends Pattern {

    
    public Pattern2(String s) {
        super(s);
        length=(int)Math.ceil((double)s.length()/2);
    }
    
    @Override
    public boolean match(String word) {
        if (word.length() != this.length) return false;
        
        char[] charArray=this.sPattern.toCharArray();
        for (char c:word.toCharArray()) {
            boolean find=false;
            for (int i=0;i<charArray.length;i++) {
                if (c==charArray[i]) {
                    charArray[i]=0;
                    find=true;
                    break;
                }
            }
            if (!find) return false;
        }
        return true;
    }
}
