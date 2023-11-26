/**
 * Representation of a Research Station with control of Radio Dishes
 * @author Faycal Kilali
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ResearchStation<K, V> {

    private Map<String, RadioDish> radioDishes;
    private RadioDishNames chooseDishName = RadioDishNames.ALPHA;

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



    /**
     * Constructs a Research Station
     */
    ResearchStation(){
        radioDishes = new Map<String, RadioDish>();
    }


    /**
     * Adds a Radio Dish to the Research Station with a legal name as a key.
     */
    public void addDish() {

        String dictatedName = "";
        RadioDishNames chosenName = RadioDishNames.ZETA;

        for (RadioDishNames c : RadioDishNames.values()) {
            if (c.equals(RadioDishNames.OMEGA)) {
                chooseDishName = RadioDishNames.ALPHA;
                dictatedName = c.getDishName();
                chosenName = c;
                break;
            } else if (c.equals(chooseDishName)) {
                dictatedName = c.getDishName();
                chosenName = c;
                break;
            }
        }

        RadioDish radioDish = new RadioDish();
        radioDish.setName(chosenName.getDishName());

        radioDishes.put(dictatedName, radioDish);

    }

    /**
     * Removes a Radio Dish from the research station
     * @param key the legal name of the Radio Dish
     */
    public void removeDish(String key){
        radioDishes.remove(key);
    }


    /**
     * Requests a particular Radio Dish to begin interpreting
     * @param inChar the character to receive and interpret
     */
    public char listen(RadioDish inDish, char inChar){
        return inDish.receive(inChar);
    }

    /**
     * Returns a list of all the Radio Dishes in the order they were added, by their keys.
     * @return the list of all radio dishes
     */
    public ArrayList<String> getAllRadioDishes(){
        return radioDishes.getKeys();
    }

    /**
     * Generates a random three-word phrase based on a number of pre-determined choices of several flavours.
     * @return the generated phrase
     */
    private String generateMessage() {
        String scientificWord = getRandomWord(scientificWords);
        String technologicalWord = getRandomWord(technologicalWords);
        String mysteriousWord = getRandomWord(mysteriousWords);

        int randomOrder = new Random().nextInt(6); // Number of permutations we want

        switch (randomOrder) {
            case 0:
                return scientificWord + " " + technologicalWord + " " + mysteriousWord;
            case 1:
                return scientificWord + " " + mysteriousWord + " " + technologicalWord;
            case 2:
                return technologicalWord + " " + scientificWord + " " + mysteriousWord;
            case 3:
                return technologicalWord + " " + mysteriousWord + " " + scientificWord;
            case 4:
                return mysteriousWord + " " + scientificWord + " " + technologicalWord;
            case 5:
                return mysteriousWord + " " + technologicalWord + " " + scientificWord;
            default:
                // This should not happen
                return "";
        }
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


}
