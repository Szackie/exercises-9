package Exercise9;

class WordAmount implements Comparable {
    private String word;
    private Integer amount = 1;

    WordAmount(String word, Integer value) {
        this.word = word;
        this.amount = value;
    }

    String getWord() {
        return word;
    }

    void setWord(String word) {
        this.word = word;
    }

    Integer getAmount() {
        return amount;
    }

    void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        return ((WordAmount) o).getWord().equals(this.word);
    }

    @Override
    public int compareTo(Object o) {
        if (this.getAmount() < ((WordAmount) o).getAmount())
            return -1;
        else if (this.getAmount() == ((WordAmount) o).getAmount())
            return 0;
        else
            return 1;
    }

    public String toString() {
        return this.word + " - " + this.amount;
    }
}
