import java.util.LinkedList;

public class Crawler {

    public static void main(String[] args) {
        String[] arg = new String[]{"http://www.fsb.ru/","3", "4"};
        String url = arg[0];
        int maxDepth = Integer.parseInt(arg[1]);
        int numThreads = Integer.parseInt(arg[2]);

        //создаем пулл потоков, добавляем пару
        URLPool pool = new URLPool(maxDepth);
        //поместим указанный пользователем адрес в пул с глубиной 0
        pool.addPair(new URLDepthPair(url,0));
        //создаем количество задач(и потоков для их выполнения)
        //каждой задаче даем ссылку на созданный пулл
        for(int i = 0; i < numThreads; i ++){
            CrawlerTask c = new CrawlerTask(pool);
            Thread t = new Thread(c);
            t.start();
        }
        while(numThreads != pool.getWait()){
            try{
                //проверка по таймеру
                Thread.sleep(500);
            }
            catch (InterruptedException e){}
        }
        try{
            showResult(pool.getViewedLinks());
        }
        catch(NullPointerException e){
            System.out.println("usage: java Crawler " + arg[0] + " " + arg[1] + " " + arg[2]);
        }
        System.exit(0);
    }

    //ввыодим список найденных адресов
    public static void showResult(LinkedList<URLDepthPair> list){
        for (URLDepthPair c:list){
            System.out.println("Depth : "+c.getDepth() + "\tLink : "+c.getUrl());
        }
    }
}



