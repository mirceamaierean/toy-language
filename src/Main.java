import controller.Controller;
import controller.IController;
import model.exceptions.AppException;
import repository.IRepository;
import repository.Repository;
import view.IMainView;
import view.MainView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // /Users/mirceamaierean/IdeaProjects/toy-language-map/src/log.txt
    // print(1);
    // IntegerType v;v=2;print(v);
    // StringType a;a="Hello World!";print(a);
    // IntegerType a;a = 5;IntegerType b;b = a - (4 * (9 / 2) + 23) / 2 + 7;print(b);
    // IntegerType a;IntegerType b;a=10;b=5;if (a<b) then print(a);else print(b);
    // BooleanType a;a=true;BooleanType b;print(a or b);
    // StringType varf;varf="test.txt";openRFile(varf);IntegerType varc;readFile(varf,varc);print(varc);readFile(varf,varc);print(varc);closeRFile(varf);
    // StringType varf;varf="test.txt";openRFile(varf);IntegerType varc;readFile(varf,vara);print(varc);readFile(varf,varc);print(varc);closeRFile(varf);
    public static void main(String[] args) throws IOException, AppException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Select logfilePath:");
//        if (line.isEmpty())
//        line = null;
        String line = "log.txt";
//        String line = "/Users/mirceamaierean/IdeaProjects/toy-language-map/src/log.txt";
//        String line = reader.readLine().trim();

        IRepository repository = new Repository(line);
        IController controller = new Controller(repository, false);
        IMainView view = new MainView(controller);
        view.run();
    }
}