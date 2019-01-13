import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Test {

    @Test
    @DisplayName("Assert that True is equals to True")
    fun assert_true_true() {
        assertThat(true).isTrue()
    }

    @ParameterizedTest(name = "Size of {0} should be {1}")
    @CsvSource("Hello, 5", "World, 5", "ApiumHub, 8")
    @DisplayName("Assert Size of words")
    fun assert_size_of_words(word: String, size: Int) {
        assertThat(word.length).isEqualTo(size)
    }
}
