import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { POST_CREATE_NOT_ENOUGH_INFORMATION } from '../../constants/Messages';
import PostCreateBody from '../organisms/postcreate/PostCreateBody';
import PostCreateButtons from '../organisms/postcreate/PostCreateButtons';
import PostCreateDescription from '../organisms/postcreate/PostCreateDescription';
import PostCreateHeader from '../organisms/postcreate/PostCreateHeader';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 100%;
  align-items: center;
`;

const PostCreatePage = () => {
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [body, setBody] = useState('');
  const [image, setImage] = useState([]);
  const navigate = useNavigate();
  const submitNewPost = () => {
    const url = 'posts';
    const postData = {
      title,
      category,
      description,
      content: body,
    };
    if (image.length) postData.imgUrls = image;

    if (title && category && description && body) {
      axios
        .post(url, postData)
        .then(res => {
          navigate(`/posts/${category}/${res.data}`);
        })
        .catch(err => alert(err.message));
    } else {
      alert(POST_CREATE_NOT_ENOUGH_INFORMATION);
    }
  };

  return (
    <Container>
      <PostCreateHeader title={title} setTitle={setTitle} curCategory={category} setCategory={setCategory} />
      <PostCreateDescription description={description} setDescription={setDescription} />
      <PostCreateBody body={body} setBody={setBody} setImage={setImage} />
      <PostCreateButtons submitNewPost={submitNewPost} />
    </Container>
  );
};

export default PostCreatePage;
