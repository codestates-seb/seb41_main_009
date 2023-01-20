import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';

import axios from 'axios';
import LogIn from './components/pages/LogInPage';
import Header from './components/organisms/Header';
import PostList from './components/pages/PostListPage';
import Showcase from './components/pages/ShowcasePage';
import Sidebar from './components/organisms/Sidebar';
import User from './components/pages/UserPage';
import UserEdit from './components/pages/UserEditPage';
import SeriesListPage from './components/pages/SeriesListPage';
import PostPage from './components/pages/PostPage';
import PostCreatePage from './components/pages/PostCreatePage';
import Search from './components/pages/SearchPage';
import SeriesPage from './components/pages/SeriesPage';
import Signup from './components/pages/SignupPage';
import ErrorPage from './components/pages/404ErrorPage';

import GlobalStyled from './GlobalStyle';
import PublicRoute from './routes/PublicRoute';
import PrivateRoute from './routes/PrivateRoute';
import ProtectedRoute from './routes/ProtectedRoute';

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common.Authorization = 'AUTH_TOKEN';

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <BrowserRouter>
        <Header />
        <Main>
          <Sidebar />
          <Routes>
            <Route path="/" element={<PublicRoute component={<Showcase />} />} />
            <Route path="/login" element={<PublicRoute component={<LogIn />} />} />
            <Route path="/signup" element={<PublicRoute component={<Signup />} />} />
            <Route path="/posts/" element={<PublicRoute component={<PostList />} />} />
            <Route path="/posts/:category" element={<PublicRoute component={<PostList />} />} />
            <Route path="/posts/:category/:id" element={<PublicRoute component={<PostPage />} />} />
            <Route path="/posts/new" element={<ProtectedRoute component={<PostCreatePage />} />} />
            <Route path="/series/:category" element={<PublicRoute component={<SeriesListPage />} />} />
            <Route path="/series/:category/:id" element={<PublicRoute component={<SeriesPage />} />} />
            <Route path="/users/:userId" element={<PublicRoute component={<User />} />} />
            <Route path="/users/:userId/edit" element={<PrivateRoute component={<UserEdit />} />} />
            <Route path="/search/:keyword" element={<PublicRoute component={<Search />} />} />
            <Route path="*" element={<PublicRoute component={<ErrorPage />} />} />
          </Routes>
        </Main>
      </BrowserRouter>
    </>
  );
};

export default App;

const Main = styled.div`
  display: flex;
  justify-content: center;
  /* margin-top: calc(var(--header-height) + 50px); */
  padding-top: calc(var(--header-height) + 50px);
`;
