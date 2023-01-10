import java.util.LinkedList;


public class URLPool {
    //список всех адресов и относительную глубину поиска
    LinkedList<URLDepthPair> viewedLinks;
    LinkedList<URLDepthPair> foundedLink;
    int maxDepth;
    //количество потоков в ожидании
    int waiters;

    public URLPool(int maxDepth){
        this.maxDepth = maxDepth;
        foundedLink = new LinkedList<URLDepthPair>();
        viewedLinks = new LinkedList<URLDepthPair>();
        waiters = 0;
    }

    //способ получения пары
    //synchronized необходим для того, чтобы с этим методом взаимодействовал
    //только один поток
    public synchronized URLDepthPair getPair(){
        //если ни один адрес недоступен, то режим ожидания
        while(foundedLink.size() == 0){
            waiters++;
            try{
                wait();
            }
            catch (InterruptedException e){
                System.out.println("Interrupted Exception");
            }
            waiters--;
        }
        return foundedLink.removeFirst();
    }
    //также синхронизиурем
    public synchronized void addPair(URLDepthPair pair){
        if(URLDepthPair.check(viewedLinks, pair)){
            viewedLinks.add(pair);
            if (pair.getDepth() < maxDepth){
                foundedLink.add(pair);
                //продолжаем работу потока, к которому ранее был вызван wait
                //в случае, когда новый адрес добавлен к пулу
                notify();
            }
        }
    }

    public int getWait() {
        return waiters;
    }

    public LinkedList<URLDepthPair> getViewedLinks(){
        return viewedLinks;
    }
}