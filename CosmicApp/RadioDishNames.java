package CosmicApp;

/**
 * A enumeration of all the legal radio dish names.
 * A Suffix should be added to each one, per iteration, to differentiate them if there is more than one per same call-sign.
 * @author Faycal Kilali
 * @version 1.0
 */
public enum RadioDishNames {
    ALPHA,
    BETA,
    GAMMA,
    SIGMA,
    DELTA,
    EPSILON,
    ZETA,
    ETA,
    THETA,
    IOTA,
    KAPPA,
    LAMBDA,
    MU,
    NU,
    XI,
    OMICRON,
    PI,
    RHO,
    TAU,
    UPSILON,
    PHI,
    CHI,
    PSI,
    OMEGA;

    private int suffixCounter = 0;

    public String getDishName() {
        String dishName = name() + "-" + suffixCounter;
        incrementSuffix();
        return dishName;
    }

    private void incrementSuffix() {
        suffixCounter++;
    }

    public RadioDishNames getNextDish() {
        RadioDishNames[] dishes = values();
        return dishes[suffixCounter++ % dishes.length];
    }
}