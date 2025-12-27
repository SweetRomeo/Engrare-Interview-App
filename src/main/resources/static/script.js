let questionsData = [];
let currentQuestionIndex = 0;
let userScore = 0;
let selectedOptionIndex = null;

// DOM Elementlerini Seçelim
const inputSection = document.getElementById('input-section');
const loadingSection = document.getElementById('loading-section');
const quizSection = document.getElementById('quiz-section');
const resultSection = document.getElementById('result-section');

const questionCard = document.getElementById('question-card');
const rewardCard = document.getElementById('reward-card');

// --- 1. Quiz Oluşturma (API İsteği) ---
document.getElementById('generate-btn').addEventListener('click', async () => {
    const videoUrl = document.getElementById('video-url').value;

    if (!videoUrl) {
        alert("Lütfen bir video linki girin!");
        return;
    }

    // Ekranları yönet
    inputSection.classList.add('hidden');
    loadingSection.classList.remove('hidden');

    try {
        const response = await fetch('/api/quiz/generate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                videoUrl: videoUrl,
                questionCount: 5
            })
        });

        if (!response.ok) throw new Error("API Hatası oluştu");

        const data = await response.json();

        // Backend'den gelen veri yapısına göre listeyi alıyoruz
        questionsData = data.questions || data;

        if (questionsData.length > 0) {
            startQuiz();
        } else {
            alert("Sorular oluşturulamadı. Lütfen tekrar deneyin.");
            location.reload();
        }

    } catch (error) {
        console.error(error);
        alert("Hata: " + error.message);
        location.reload();
    }
});

// --- 2. Quiz Başlatma ve Soru Yükleme ---
function startQuiz() {
    loadingSection.classList.add('hidden');
    quizSection.classList.remove('hidden');
    currentQuestionIndex = 0;
    userScore = 0;
    loadQuestion(0);
}

function loadQuestion(index) {
    // Kartları sıfırla
    questionCard.classList.remove('hidden-card');
    rewardCard.classList.add('hidden-card');

    const currentQ = questionsData[index];

    // İlerleme Çubuğu ve Sayaç
    document.getElementById('question-counter').innerText = `Soru ${index + 1} / ${questionsData.length}`;
    const progressPercent = ((index + 1) / questionsData.length) * 100;
    document.getElementById('progress-fill').style.width = `${progressPercent}%`;

    // Soru Metni (Backend'den 'question' olarak geliyor)
    document.getElementById('question-text').innerText = currentQ.question;

    // Şıkları Oluştur
    const optionsContainer = document.getElementById('options-container');
    optionsContainer.innerHTML = ''; // Önceki şıkları temizle

    currentQ.options.forEach((opt, i) => {
        const btn = document.createElement('div');
        btn.className = 'option-btn';
        btn.innerText = opt;
        btn.onclick = () => selectOption(i, btn);
        optionsContainer.appendChild(btn);
    });

    // Kontrol butonunu pasif yap
    document.getElementById('check-answer-btn').disabled = true;
    selectedOptionIndex = null;
}

function selectOption(index, btnElement) {
    // Eğer cevap zaten kontrol edildiyse seçime izin verme
    if(document.getElementById('check-answer-btn').innerText === "Lütfen Bekleyin...") return;

    // Görsel seçim işlemi
    document.querySelectorAll('.option-btn').forEach(b => b.classList.remove('selected'));
    btnElement.classList.add('selected');

    selectedOptionIndex = index;
    document.getElementById('check-answer-btn').disabled = false;
}

// --- 3. Cevap Kontrolü (GÜNCELLENMİŞ VE SORUNSUZ KISIM) ---
document.getElementById('check-answer-btn').addEventListener('click', () => {
    const currentQ = questionsData[currentQuestionIndex];
    const optionsElements = document.querySelectorAll('.option-btn');
    const checkBtn = document.getElementById('check-answer-btn');

    // 1. Doğru Cevabın İndeksini Bul (a,b,c -> 0,1,2 çevirimi)
    let correctIndex = -1;
    // Gelen veri null/undefined olsa bile patlamaması için String'e çeviriyoruz
    const answerData = String(currentQ.correctAnswer).toLowerCase().trim();

    if ("abcdefgh".includes(answerData)) {
        correctIndex = "abcdefgh".indexOf(answerData);
    } else if (!isNaN(answerData)) {
        correctIndex = parseInt(answerData);
    }

    // 2. Kontrol Mantığı
    if (selectedOptionIndex === correctIndex) {
        // --- DOĞRU CEVAP ---
        userScore += 50;
        showReward(); // Tebrikler ekranını aç
    } else {
        // --- YANLIŞ CEVAP ---
        // Pop-up yok! Sadece butonları boyuyoruz.

        // A) Seçilen şıkkı KIRMIZI yap
        if(optionsElements[selectedOptionIndex]) {
            optionsElements[selectedOptionIndex].classList.add('wrong');
        }

        // B) Asıl doğru olan şıkkı YEŞİL yap (Kullanıcı doğrusunu görsün)
        if (correctIndex !== -1 && optionsElements[correctIndex]) {
            optionsElements[correctIndex].classList.add('correct');
        }

        // Butona tekrar basılmasın diye kilitle
        checkBtn.disabled = true;
        checkBtn.innerText = "Lütfen Bekleyin...";

        // C) 2 saniye bekle ve diğer soruya geç
        setTimeout(() => {
            checkBtn.innerText = "Kontrol Et"; // Buton yazısını düzelt
            nextQuestion();
        }, 2000);
    }
});

function showReward() {
    questionCard.classList.add('hidden-card');
    rewardCard.classList.remove('hidden-card');
}

// --- 4. Sonraki Soruya Geçiş ---
document.getElementById('next-btn').addEventListener('click', nextQuestion);

function nextQuestion() {
    currentQuestionIndex++;
    if (currentQuestionIndex < questionsData.length) {
        loadQuestion(currentQuestionIndex);
    } else {
        finishQuiz();
    }
}

// --- 5. Bitiş Ekranı ---
function finishQuiz() {
    quizSection.classList.add('hidden');
    resultSection.classList.remove('hidden');
    document.getElementById('score-display').innerText = userScore;
}

document.getElementById('restart-btn').addEventListener('click', () => {
    location.reload();
});