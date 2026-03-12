public class ItemList {

    Item[] list = new Item[100];
    int numOfItem = 0;

    public boolean addItem(Item item) {

        if (numOfItem >= list.length)
            return false;

        list[numOfItem] = item;
        numOfItem++;

        return true;
    }

    public void displayAll() {

        for (int i = 0; i < numOfItem; i++) {
            System.out.println(list[i]);
        }
    }

    public Item findItem(String creator) {

        for (int i = 0; i < numOfItem; i++) {

            if (list[i].creator.equalsIgnoreCase(creator)) {
                return list[i];
            }
        }

        return null;
    }

    public void displayByType(String type) {

        for (int i = 0; i < numOfItem; i++) {

            if (type.equalsIgnoreCase("vase") && list[i] instanceof Vase)
                System.out.println(list[i]);

            if (type.equalsIgnoreCase("statue") && list[i] instanceof Statue)
                System.out.println(list[i]);

            if (type.equalsIgnoreCase("painting") && list[i] instanceof Painting)
                System.out.println(list[i]);
        }
    }
}