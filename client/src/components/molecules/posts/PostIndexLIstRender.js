import styled from 'styled-components';
import { Viewer } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import { useRef, useEffect } from 'react';
import { LabelXSmall } from '../../../styles/typo';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: calc(100% / 12 * 10);
  height: fit-content;
  margin-bottom: 10px;
  margin-top: 0px;

  .toastui-editor-contents h1 {
    ${LabelXSmall}
    color: var(--gray-500);
    margin: 0px;
    border-bottom: none;

    &:hover {
      color: var(--gray-700);
    }
  }
  .toastui-editor-contents h2 {
    display: flex;
    flex-direction: row;
    padding-left: 20px;
    color: var(--gray-500);
    ${LabelXSmall}
    gap: 6px;
    margin: 0px;
    border-bottom: none;

    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents h3 {
    display: flex;
    flex-direction: row;
    padding-left: 40px;
    color: var(--gray-500);
    gap: 6px;
    margin: 0px;
    border-bottom: none;

    ${LabelXSmall}
    &:hover {
      color: var(--gray-700);
    }
  }

  .toastui-editor-contents p {
    display: none;
  }

  .toastui-editor-contents img {
    display: none;
  }
`;

const PostIndexLIstRender = ({ post }) => {
  const viewerRef = useRef();

  useEffect(() => {
    viewerRef.current.getInstance().setMarkdown(post.content);
  }, [post]);

  return (
    <Container>
      <Viewer ref={viewerRef} initialValue={post.content} />
    </Container>
  );
};

export default PostIndexLIstRender;
