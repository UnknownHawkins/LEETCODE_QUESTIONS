class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){
            if(Character.isLetterOrDigit(ch)){
            sb.append(Character.toLowerCase(ch));
        }
    }
    if(sb.toString().equals(sb.reverse().toString())){
    return true;
    }
    return false;
}
}
