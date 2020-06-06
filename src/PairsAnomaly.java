public class PairsAnomaly extends Heuristic {
    private BoardState eState;
    public PairsAnomaly(BoardState eState){
        this.eState=eState;
    }


    //calculate with colors!!
    @Override
    public int getH(BoardState boardState){
        int h = 0;
        int rows = boardState.getRows();
        int columns = boardState.getColumns();
        for(int i=0;i<rows;++i){
            for(int j=0;j<columns;++j){
                for(int k=i;k<rows;k++){
                    for(int s = j;s<columns;++s){
                        if(i!=k || j!=s ){
                            int value1 = boardState.getBoard()[i][j];
                            int value2 = boardState.getBoard()[k][s];
                            if(value2<value1 && value2!=0 && value1!=0){
                                int value1Price = boardState.getPriceOfValue(value1);
                                int value2Price = boardState.getPriceOfValue(value2);
                                h+=Math.max(value1Price,value2Price);
                            }
                        }
                    }
                }
            }
        }
        return h;
    }

}
