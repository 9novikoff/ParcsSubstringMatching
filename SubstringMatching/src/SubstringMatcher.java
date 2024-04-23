import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class SubstringMatcher implements AM {
    public void run(AMInfo info) {

        String text = (String)info.parent.readObject();
        String pattern = (String)info.parent.readObject();

        List<int> res = new List<>();

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            boolean isMatch = true;
            for(int j = 0; j < pattern.length(); j++){
                if(text[i+j] != pattern[j]){
                    isMatch = false;
                    break;
                }
            } 

            if(isMatch){
                res.add(i);
            }
        }

        info.parent.write(res);
    }
}
