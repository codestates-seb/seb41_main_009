import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Sidebar from './components/organisms/Sidebar';
import SeriesList from './components/templates/SeriesList';
import PostPage from './components/templates/PostPage';
import PostCreatePage from './components/templates/PostCreatePage';
import SeriesPage from './components/templates/SeriesPage';

const App = () => {
  return (
    <>
      <Reset />
      <Navigator />
      <Sidebar />
      <BrowserRouter>
        <Routes>
          <Route path="/signin" element={<Signin />} />
          <Route path="/posts" element={<PostList />} />
          <Route path="/posts/:id" element={<PostPage />} />
          <Route path="/posts/new" element={<PostCreatePage />} />
          <Route path="/series" element={<SeriesList />} />
          <Route path="/series/:id" element={<SeriesPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
