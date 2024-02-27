package circuit;
public class CircuitResistor {
    private int resistor1, resistor2;
    private double currentS1;
    private double currentS2;
    private double res;
    private double source;
    private boolean isSerial;

    //region SET

    /**
     * sets value of resistor1
     * @param resistor1 input
     * @throws Exception if input is lower than zero
     */
    public void setResistor1(int resistor1) throws Exception{
        if(resistor1 > 0){
            this.resistor1 = resistor1;
        }else{
            throw new Exception("Nelze zadat hodnotu která je menší nebo rovna 0");
        }
    }

    /**
     * sets value of resistor2
     * @param resistor2 input
     * @throws Exception if input is lower than zero
     */
    public void setResistor2(int resistor2) throws Exception {
        if(resistor2 > 0){
            this.resistor2 = resistor2;
        }else{
            throw new Exception("Nelze zadat hodnotu která je menší nebo rovna 0");
        }
    }

    /**
     * sets the value of source
     * @param source input
     * @throws Exception if input is lower than zero
     */
    public void setSource(int source) throws Exception{
        if(source > 0){
            this.source = source;
        }else{
            throw new Exception("Nelze zadat hodnotu která je menší nebo rovna 0");
        }
    }

    /**
     * sets value of boolean isSerial
     * @param opt input
     * @throws Exception if input is anything else than 0 or 1
     */
    public void setSerial(int opt) throws Exception {
        if(opt == 0){
            this.isSerial = true;
        }else if(opt == 1){
            this.isSerial = false;
        }else{
            throw new Exception("Neznámé číslo, 0 = Sériové zapojení, 1 = paraelní zapojení");
        }
    }
    //endregion

    /**
     * Returns String diagram of the circuit
     * @return the Diagram
     */
    public String diagram(){
        if(isSerial){
            return """
                      _  \s

                      *--------------|
                      *              |
                      *              |
                      *              |
                      *    -----     |
                    * * *      |     |
                    *   *      |     |
                    *   *      |     |
                    *   * R2   | Ur2 |
                    *   *      |     |
                    *   *      |     |
                    * * *      |     |
                      *    -----     |
                      *              |
                      *              |
                      *              |
                      *              |
                      *   -----      |
                    * * *     |      |
                    *   *     |      |
                    *   *     |      |
                    *   * R1  | Ur1  |
                    *   *     |      |
                    *   *     |      |
                    * * *     |      ⌃ U
                      *   -----      |
                      *              |
                      ⌃ I            |
                      *              |
                      *              |
                      *--------------|

                      +""".indent(1);
        }else{
            return """
                      -               -     -----|
                      *               *          |
                      *               *          |
                      *               *          |
                      *               *          |
                      *    ---        *    ---   |
                    * * *    |      * * *    |   |
                    *   *    |      *   *    |   |
                    *   *    |      *   *    |   |
                    *   * R1 |Ur1   *   * R2 |Ur2|
                    *   *    |      *   *    |   |
                    * * *    |      * * *    |   |
                      *    ---        *      |   |
                      *               *    ---   |
                      *               *          |
                      ⌃I3             ⌃I2        |
                      *               *          |
                      * * * * * * * * *          |
                              *                  |
                              *                  |
                              *                  |
                              ⌃I                 ⌃U
                              *                  |
                              *                  |
                              *                  |
                              +     -------------|""".indent(1);
        }
    }

    /**
     * returns resistance of the circuit
     * @return circuit resistance in double
     */
    public double resistance(){
        double resTemp;
        if(isSerial){
            resTemp = resistor1 + resistor2;
        }else{
            resTemp = resistor1*resistor2/(resistor1+resistor2);
        }
        res = resTemp;
        return Math.floor(resTemp*1000)/1000;
    }

    /**
     * returns main current of the circuit
     * @return main current as double
     */
    public double currnet(){
        double current = source / res;
        return Math.floor(current *1000)/1000;
    }

    /**
     * returns side current I2 if circuit is not serial
     * @return current I2 as double
     * @throws Exception if circuit is serial
     */
    public double currnetS1()throws Exception{
        if(isSerial){
            throw new Exception("U sériového zapojení se proud nedělí!");
        }else{
            currentS1 = source/resistor1;
            return Math.floor(currentS1*1000)/1000;
        }
    }

    /**
     * returns side current I3 if circuit is not serial
     * @return current I3 as double
     * @throws Exception if circuit is serial
     */
    public double currnetS2()throws Exception{
        if(isSerial){
            throw new Exception("U sériového zapojení se proud nedělí!");
        }else{
            currentS2 = source/resistor2;
            return Math.floor(currentS2*1000)/1000;
        }
    }

    /**
     * returns voltage on resistor1
     * @return voltage on resistor1 as double
     */
    public double res1V(){
        double res1V;
        if(isSerial){
            res1V = resistor1 * currnet();
        }else{
            res1V = currentS1 * resistor1;
        }
        return Math.floor(res1V *1000)/1000;
    }

    /**
     * returns voltage on resistor2
     * @return voltage on resistor2 as double
     */
    public double res2V(){
        double res2V;
        if(isSerial){
            res2V = resistor2 * currnet();
        }else{
            res2V = currentS2 * resistor2;
        }
        return Math.floor(res2V *1000)/1000;
    }
}