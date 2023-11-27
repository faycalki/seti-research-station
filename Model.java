import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Holds the logic of the data and performs operations on it. Takes orders from Controller, and communicates the progress to Controller.
 * @author Faycal Kilali
 * @version 1.0
 */
public class Model {

    private ArrayList<ResearchStation<String, RadioDish>> researchStations;
    private String username;
    private Controller controller;

    private ResearchStation<String, RadioDish> researchStation;

    private String generatedMessage;


    private static final List<String> scientificWords = Arrays.asList(
            "quantum", "cosmic", "nebula", "singularity", "graviton", "dark matter", "quasar", "hyperspace", "antimatter", "resonance",
            "xenon", "radiation", "luminosity", "extraterrestrial", "entanglement", "aether", "pulsar", "spectral", "heliopause", "parallax",
            "entropy", "astronomy", "biochemistry", "biophysics", "botany", "cellular", "chemistry", "cryogenics", "geophysics", "genetics",
            "mathematics", "microbiology", "nanoscience", "oceanography", "optics", "particle", "physics", "psychophysics", "radiobiology", "seismology",
            "thermodynamics", "astrophysics", "biotechnology", "cosmology", "cybernetics", "ecology", "electromagnetic", "magnetism", "meteorology", "neuroscience",
            "nuclear", "palaeontology", "parasitology", "pharmacology", "psychology", "quantum mechanics", "scientific", "taxonomy", "virology", "zoology",
            "isotope", "proton", "neutron", "electron", "cosmonaut", "anatomy", "baryon", "big bang", "black hole", "climate change",
            "cloning", "dark energy", "doppler effect", "electron microscope", "fermentation", "fission", "fusion", "genome", "gravitational", "Hawking radiation",
            "Higgs boson", "light-year", "molecule", "mutation", "Newton's laws", "orbit", "photon", "quantum entanglement", "radiation therapy", "Schrodinger's cat",
            "solar flare", "string theory", "superconductor", "theory of relativity", "thermal expansion", "vaccine", "antibiotic", "zero gravity", "photosynthesis", "ecosystem",
            "genetic mutation", "organic chemistry", "symbiosis", "element", "DNA", "RNA", "homeostasis", "cytoplasm", "nucleus", "mitochondria"
            // 100 words in total
    );


    private static final List<String> technologicalWords = Arrays.asList(
            "nanotechnology", "cybernetic", "biomechanics", "teleportation", "augmented reality", "genetic engineering", "robotics", "AI",
            "quantum computing", "exoskeleton", "neural interface", "bionics", "synthetic biology", "cybersecurity", "hyperspectral imaging",
            "telecommunication", "biorobotics", "virtual reality", "nanorobotics", "bioinformatics",
            "algorithm", "automation", "cloud computing", "cryptocurrency", "data mining", "drones", "machine learning", "nanomedicine", "photonics", "radiotechnology",
            "space exploration", "supercomputing", "telemedicine", "wearable technology", "3D printing", "biometric", "blockchain", "internet of things", "smart grid", "quantum cryptography",
            "wireless charging", "augmented intelligence", "autonomous vehicles", "biometric authentication", "brain-machine interface", "cryptographic key", "decentralized", "digital twin", "edge computing", "fog computing",
            "genomic data", "hydrogen fuel cells", "intelligent agents", "laser cutting", "mixed reality", "neuromorphic", "optical computing", "quantum key distribution", "resistive random access memory", "self-driving",
            "smart home", "smart materials", "virtual assistant", "wireless mesh network", "zero-day attack", "ambient intelligence", "bio-hacking", "cloud robotics", "quantum dot display", "quantum sensor",
            "adaptive cybersecurity", "affective computing", "ambient computing", "augmented analytics", "blockchain as a service", "brain-computer interface", "cognitive computing", "collective intelligence", "conversational AI", "cryptographic hash function",
            "cryptocurrency wallet", "decentralized finance", "digital identity", "edge analytics", "edge device", "encryption", "extended reality", "federated learning", "fintech", "holographic display", "homomorphic encryption"
            // 100 words in total
    );


    private static final List<String> mysteriousWords = Arrays.asList(
            "enigma", "cryptic", "abyss", "anomaly", "cryptogram", "sibylline", "occult", "esoteric", "enigmatic", "oracle",
            "mystify", "serendipity", "whimsical", "conundrum", "paradox", "arcane", "metaphysical", "ethereal", "cipher", "portal",
            "astrology", "divination", "eschatology", "mysticism", "parapsychology", "phenomenon", "premonition", "psychic", "surreptitious", "telepathy",
            "transcendental", "unearthly", "cryptid", "fortuneteller", "hallucination", "levitation", "mirage", "occultism", "phantasm", "revelation",
            "spiritualism", "supernatural", "telekinesis", "teleportation", "clairvoyance", "cryptozoology", "dowsing", "extrasensory", "ghost", "haunting",
            "illusion", "levitate", "mystical", "necromancy", "omen", "poltergeist", "psychometry", "sorcery", "specter", "witchcraft",
            "alchemy", "apport", "astral", "bewitch", "coven", "curse", "divine", "enchant", "goblin", "hex",
            "illusionist", "incantation", "jinx", "magic", "oracle", "potion", "quiver", "rune", "seance", "shaman",
            "spell", "spirit", "talisman", "trance", "vampire", "voodoo", "wand", "warlock", "wicca", "wizard",
            "zombie", "changeling", "sorcerer", "sorceress", "spellbound", "spellcaster", "abracadabra", "amulet", "arcane", "bewitched"
            // 100 words in total
    );

    public Model(Controller controller){
        this.controller = controller;
        researchStations = new ArrayList<>();
        username = "";
    }


    /**
     * Accessor method for the username
     * @return the username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Mutator method for username
     * @param username the username to set to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves all Research Stations
     * @return List of all research stations as a list
     */
    public ArrayList<ResearchStation<String, RadioDish>> getAllResearchStations() {
        return researchStations;
    }

    /**
     * Adds a Research Station to the list of name inString
     * @param inString the name of the Research Station to create and add
     */
    public void addResearchStation(String inString) {
        researchStations.add(new ResearchStation<String, RadioDish>(inString));
    }

    /**
     * Removes the research station
     * @param inString the name of the Research Station to remove
     */
    public void removeResearchStation(String inString) {
        researchStations.remove(inString);
    }

    /**
     * Retrieves the current Research Station
     * @return current Research Station
     */
    public ResearchStation<String, RadioDish> getResearchStation() {
        return researchStation;
    }

    /**
     * If we decide to have multiple research stations in the future for our simulation or to swap between them
     * @param researchStation the research station that'll be our assigned one to work with at the time.
     */
    public void setResearchStation(ResearchStation<String, RadioDish> researchStation) {
        this.researchStation = researchStation;
    }

    /**
     * Resets the Research Station
     * @implSpec This does not reset the order of names or suffixes.
     * @return true if successful, false otherwise.
     */
    public boolean resetResearchStation(){
        ResearchStation<String, RadioDish> newStation = new ResearchStation();
        setResearchStation(newStation);

        if (researchStation.equals(newStation)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Retrieves the station's calibration
     * @return the station's calibration
     */
    public double getStationCalibration(){
        return researchStation.getStationCalibration();
    }


    /**
     * Adds a radio dish to the station
     * @return true if successful, false otherwise
     */
    public boolean addDish(){
        researchStation.addDish();
        return true;
    }

    /**
     * For simplicitly, this removes the last-added radio dish
     */

    public boolean removeDish() {
        // Implement the logic for removing a radio dish from the current station
        ArrayList<String> keys = researchStation.getAllRadioDishes();
        if (!keys.isEmpty()) {
            researchStation.removeDish(keys.get(keys.size() - 1));
            return true;
        }
        return false;
    }

    /**
     * Generates a random three-word phrase based on a number of pre-determined choices of several flavours.
     * @return the generated phrase
     */
    public String generateMessage() {
        String scientificWord = getRandomWord(scientificWords);
        String technologicalWord = getRandomWord(technologicalWords);
        String mysteriousWord = getRandomWord(mysteriousWords);

        int randomOrder = new Random().nextInt(6); // Number of permutations we want

        String message = "";

        switch (randomOrder) {
            case 0:
                message = scientificWord + " " + technologicalWord + " " + mysteriousWord;
                break;
            case 1:
                message =  scientificWord + " " + mysteriousWord + " " + technologicalWord;
                break;
            case 2:
                message =  technologicalWord + " " + scientificWord + " " + mysteriousWord;
                break;
            case 3:
                message =  technologicalWord + " " + mysteriousWord + " " + scientificWord;
                break;
            case 4:
                message =  mysteriousWord + " " + scientificWord + " " + technologicalWord;
                break;
            case 5:
                message =  mysteriousWord + " " + technologicalWord + " " + scientificWord;
                break;
            default:
                // This should not happen
                return "";
        }
        generatedMessage = message;
        return message;
    }

    public String getGeneratedMessage(){
        return generatedMessage;
    }

    /**
     * Chooses a random word from a List.
     * @param wordList the list of words to choose from
     * @return the chosen random word.
     */
    private String getRandomWord(List<String> wordList) {
        Random random = new Random();
        int index = random.nextInt(wordList.size());
        return wordList.get(index);
    }




    public char initiateListening() {
    return 'a';
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }


}
