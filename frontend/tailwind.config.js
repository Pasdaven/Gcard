/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        bg: '#1F232D',
        card: '#252A36',
        box: '#323847',
        main: '#5A69E6',
        tGray: '#B8B8B8',
      },
      keyframes: {
        godog: {
          '0%': {
            left: 0,
            top: 0,
          },
          '50%': {
            left: '350px',
            transform: 'rotate(-45deg)',
          },
          '100%': {
            left: '350px',
            top: '-225px',
          },
        },
        wheels: {
          '0%': { transform: 'translateY(0)', opacity: '0' },
          '25%': { transform: 'translateY(5px)', opacity: '0.3' },
          '50%': { transform: 'translateY(10px)', opacity: '0.5' },
          '75%': { transform: 'translateY(5px)', opacity: '0.8' },
          '100%': { transform: 'translateY(0)', opacity: '1' },
        },
      },
      animation: {
        godog: 'godog 3s ease-in-out 1',
        wheels: 'wheels 2s linear 1',
      },
    },
  },
  plugins: [
    require('tailwind-scrollbar-hide'),
    require('tailwindcss-animation-delay'),
  ],
}
