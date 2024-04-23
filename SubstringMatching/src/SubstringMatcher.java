import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class SubstringMatcher implements AM {
    public void run(AMInfo info) {

        String text = (String)info.parent.readObject();
        String pattern = (String)info.parent.readObject();

        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            boolean isMatch = true;
            for(int j = 0; j < pattern.length(); j++){
                if(text.charAt(i+j) != pattern.charAt(j)){
                    isMatch = false;
                    break;
                }
            } 

            if(isMatch){
                res.add(i);
            }
        }

        info.parent.write((Serializable)res);
    }
}
