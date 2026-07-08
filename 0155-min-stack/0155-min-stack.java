class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minstack;
    public MinStack() {
        stack=new Stack<>();
        minstack=new Stack<>();
    }  
    public void push(int val) {
        stack.push(val);
        if(minstack.empty() || val<=minstack.peek()){
            minstack.push(val);
        }
    }   
    public void pop() {
        int popped =stack.pop();
        if(popped==minstack.peek()){
            minstack.pop();
        }
    }
    public int top() {
        int peek=stack.peek();
        return peek;
    } 
    public int getMin() {
        int min=minstack.peek();
        return min;        
    }
}
