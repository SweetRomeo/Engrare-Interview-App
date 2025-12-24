$(document).ready(function() {

    // Değişkenlerimizi tutalım
    let quizData = null;
    let userAnswers = {};

    // 1. "Quiz Oluştur" Butonuna Tıklama
    $('#generate-btn').click(function() {
        const videoUrl = $('#video-url').val();

        if (!videoUrl) {
            alert("Lütfen bir video linki girin!");
            return;
        }

        // Ekranları ayarla (Loading göster, diğerlerini gizle)
        $('#input-section').addClass('hidden');
        $('#loading-section').removeClass('hidden');
        $('#result-section').addClass('hidden');
        $('#quiz-section').addClass('hidden');

        // Backend'e İstek At (AJAX)
        $.ajax({
            url: 'http://localhost:8081/api/quiz/generate',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                videoUrl: videoUrl,
                language: "Türkçe",
                questionCount: 3
            }),
            timeout: 300000, // 5 Dakika timeout
            success: function(response) {
                console.log("Sorular Geldi:", response);
                quizData = response;
                renderQuiz(); // Soruları ekrana bas
            },
            error: function(error) {
                console.error("Hata:", error);
                alert("Bir hata oluştu. Konsolu kontrol et.");
                $('#input-section').removeClass('hidden'); // Geri dön
            },
            complete: function() {
                $('#loading-section').addClass('hidden');
            }
        });
    });

    // 2. Soruları Ekrana Basma Fonksiyonu
    function renderQuiz() {
        const container = $('#questions-container');
        container.empty(); // Temizle
        userAnswers = {}; // Cevapları sıfırla

        quizData.questions.forEach((q, index) => {
            // HTML oluşturma (Template Literal)
            let questionHtml = `
                <div class="question-card" data-index="${index}">
                    <h3>${index + 1}. ${q.question}</h3>
                    <div class="options-grid">
                        ${q.options.map(option => `
                            <button class="option-btn">${option}</button>
                        `).join('')}
                    </div>
                </div>
            `;
            container.append(questionHtml);
        });

        $('#quiz-section').removeClass('hidden');
    }

    // 3. Şık Seçme Mantığı (Event Delegation)
    // Dinamik oluşturulan butonlar için 'on' kullanıyoruz
    $(document).on('click', '.option-btn', function() {
        // Tıklanan butonu seç
        const btn = $(this);
        const questionIndex = btn.closest('.question-card').data('index');
        const selectedOption = btn.text();

        // Görsel efekt: Diğerlerinden 'selected'ı kaldır, buna ekle
        btn.siblings().removeClass('selected');
        btn.addClass('selected');

        // Cevabı kaydet
        userAnswers[questionIndex] = selectedOption;
    });

    // 4. Testi Bitir ve Hesapla
    $('#finish-btn').click(function() {
        let score = 0;
        let total = quizData.questions.length;

        quizData.questions.forEach((q, index) => {
            if (userAnswers[index] === q.correctAnswer) {
                score++;
            }
        });

        // Sonucu ekrana yaz
        $('#score-display').text(`${score} / ${total} Doğru`);

        // Ekran değiştir
        $('#quiz-section').addClass('hidden');
        $('#result-section').removeClass('hidden');
    });

    // 5. Başa Dön
    $('#restart-btn').click(function() {
        location.reload();
    });

});