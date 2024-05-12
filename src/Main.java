import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Main {

    public static void main(String[] args) {
	/*	требуется проделать следующие шаги: Домашнее задание
Произвести распаковку архива в папке savegames.
Произвести считывание и десериализацию одного из разархивированных файлов save.dat.
Вывести в консоль состояние сохранненой игры.

Реализация
Реализуйте метод openZip(), который принимал бы два аргумента: путь к файлу типа String (например,
"/Users/admin/Games/GunRunner/savegames/zip.zip") и путь к папке, куда стоит разархивировать файлы типа String (например,
"/Users/admin/Games/GunRunner/savegames"). Для распаковки Вам потребуются такие стримовые классы как FileInputStream,
ZipInputStream и класс объекта архива ZipEntry.
Считывание элементов аврхива производится с помощью метода getNextEntry() класса ZipInputStream,
а узнать название объекта ZipEntry можно с помощью метода getName().
Запись в файл распакованных объектов можно произвести с помощью FileOutputStream.
Далее реализуйте метод openProgress(),
который бы в качестве аргумента принимал путь к файлу с сохраненной игрой типа String (например,
"/Users/admin/Games/GunRunner/savegames/save2.dat") и возвращал объект типа GameProgress.
В данном методе Вам потребуются классы FileInputStream и ObjectInputStream.
С помощью метода класса ObjectInputStream readObject() можно десериализовать объект,
а далее привести (скастить) его к GameProgress.
Так как в классе GameProgress метод toString() уже переопределен,
поэтому достаточно вывести полученный объект в консоль.
	 */
        //Путь к файлу зип
        String pathFileZip = "D:\\Мои файлы\\Нетология\\JavaCore\\Stream API_работа с файлами и сборка проектов\\Потоки ввода вывода_Работа с файлами_Сериализация\\Домашнее задание\\Установка\\Games\\savegames\\out_zip.zip";
        //Путь к конечной папке (там же где и зип в этом случае)
        String pathDirOut = "D:\\Мои файлы\\Нетология\\JavaCore\\Stream API_работа с файлами и сборка проектов\\Потоки ввода вывода_Работа с файлами_Сериализация\\Домашнее задание\\Установка\\Games\\savegames\\";
        openZip(pathFileZip, pathDirOut);
    }

    public static void openZip(String pathFileZip, String pathDirOut) {
        //
        try (ZipInputStream zipIS = new ZipInputStream(new FileInputStream(pathFileZip))) {
            System.out.println("Start");
            ZipEntry zE;
            String s;

            while ((zE = zipIS.getNextEntry()) != null) {
                System.out.println("Считываем имя файла");
                s = zE.getName();
                System.out.println(s);

                FileOutputStream fos = new FileOutputStream(pathDirOut + s);
                for (int c = zipIS.read(); c != -1; c = zipIS.read()) {
                    fos.write(c);
                }
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathDirOut + s));
                GameProgress gameProgress = (GameProgress) ois.readObject();
                System.out.println(gameProgress.toString());
                fos.flush();
                zipIS.closeEntry();
                fos.close();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }
}
