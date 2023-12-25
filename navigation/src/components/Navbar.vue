<script setup>
import { ref } from 'vue';

const isVertical = ref(false);
const showRestartPopup = ref(false);
const showTimePopup = ref(false);
const isPaused = ref(false);

const toggleNavbarPosition = () => {
  isVertical.value = !isVertical.value;
};

const togglePause = () => {
  isPaused.value = !isPaused.value;
};

const restart = () => {
  console.log('Restarting the puzzle...');
  showRestartPopup.value = false;
};

let offsetX = ref(0);
let offsetY = ref(0);
let currentElement = ref(null);

const startDrag = (e, element) => {
  currentElement.value = element;
  offsetX.value = e.clientX - e.currentTarget.getBoundingClientRect().left;
  offsetY.value = e.clientY - e.currentTarget.getBoundingClientRect().top;
  document.addEventListener('mousemove', drag);
  document.addEventListener('mouseup', stopDrag);
};

const drag = (e) => {
  if (currentElement.value === 'navbar') {
    // Adjust this logic to move the navbar
  } else if (currentElement.value === 'restartPopup') {
    // Adjust this logic to move the restart popup
  }
};

const stopDrag = () => {
  document.removeEventListener('mousemove', drag);
  document.removeEventListener('mouseup', stopDrag);
  currentElement.value = null;
};
</script>

<template>
  <head>
    <!-- <link rel="stylesheet" href="../app.css"> -->
    <!-- 引用了 Font Awesome 图标库的 CSS 样式表 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  </head>
  <div class="container">
    <!-- Restart Popup -->
    <div v-if="showRestartPopup" class="custom-popup">
      <div class="custom-header" @mousedown="startDrag($event, 'restartPopup')">
        <span class="closeBtn" @click="showRestartPopup = false">&times;</span>
      </div>
      <div class="custom-content">
        <div class="top">
          <i class="fas fa-power-off"></i>
          <p class="warn">Restart puzzle</p>
        </div>
        <p class="message">You are about to clear the puzzle. Any saved data will be lost.</p>
        <div class="buttons">
          <button class="button1" @click="restart">Restart</button>
          <button class="button2" @click="showRestartPopup = false">Cancel</button>
        </div>
      </div>
    </div>
    <!-- Time Popup -->
    <div v-if="showTimePopup" class="popup">
      <div class="header">
        <span class="closeBtn" @click="showTimePopup = false">&times;</span>
      </div>
      <div class="Pbottom">
        <div id="time">00:00:00</div>
        <button id="stopBtn" @click="togglePause">{{ isPaused ? 'Resume' : 'Pause' }}</button>
      </div>
    </div>
    <!-- Navbar -->
    <div :class="['navbar', { vertical: isVertical, horizontal: !isVertical }]" @mousedown="startDrag($event, 'navbar')">
      <i :class="['fas', isVertical ? 'fa-chevron-down' : 'fa-chevron-right']" @click="toggleNavbarPosition"></i>
      <i class="fa-solid fa-magnifying-glass-plus"></i>
      <i class="fas fa-search-minus"></i>
      <i class="fas fa-undo"></i>
      <i class="fas fa-redo"></i>
      <i class="fas fa-power-off" @click="showRestartPopup = true"></i>
      <i class="fas fa-check"></i>
      <i class="fas fa-pencil-alt"></i>
      <i class="fas fa-clock" @click="showTimePopup = true"></i>
      <i class="fas fa-eye"></i>
      <i class="fas fa-wrench"></i>
      <i class="fas fa-info-circle"></i>
      <i class="fas fa-question-circle"></i>
      <i class="fas fa-print"></i>
      <i class="fas fa-save"></i>
    </div>
  </div>
</template>

<style scoped>
@import "../app.css";
</style>
