public class Linked {
    Node head;

    public Linked(){
        head=null;
    }
    public void addHead(int CustomerNo,CustomerData customerData){
        Node newHead=new Node(CustomerNo,customerData);
        newHead.next=head;
        head=newHead;
    }
    public void addLast(int CustomerNo,CustomerData customerData){
        Node newOne = new Node(CustomerNo,customerData);
        if(head==null){
            head=newOne;
            return;
        }
        Node temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=newOne;
    }
    public void Writer(){
        Node temp = head;
        while (temp!=null){
            System.out.println(temp.CustomerNo +" "+temp.customerData.toString());
            temp=temp.next;
        }
        System.out.println();
    }
}
class Node{
    int CustomerNo;
    CustomerData customerData;
    Node next;
    public Node(){
        CustomerNo=-1;
        customerData=new CustomerData();
        next=null;
    }
    public Node(int customerNo, CustomerData customerData) {
        CustomerNo = customerNo;
        this.customerData = customerData;
        this.next = null;
    }
}