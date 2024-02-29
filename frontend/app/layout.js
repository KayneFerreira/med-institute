import './globals.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import { Inter } from 'next/font/google'
import Navbar from './components/Navbar';
import Footer from './components/Footer';

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'Med Institute',
  description: 'Aplicação de gerenciamento de consultas médicas',
}

export default function RootLayout({ children }) {
  return (
    <html lang="pt-BR">
      <body className={inter.className}>
        <main className=''>
          <Navbar />
          {children}
          <Footer />
        </main>
      </body>
    </html>
  )
}
